package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.UserRegistrationDto;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.model.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;


@Data
@RestController
@RequestMapping("/api/user/registration")
public class RegistrationController {

    private UserService userService;
    private JavaMailSender mailSender;

    @Value("${EXPIRATION_TIME_IN_MINUTES}")
    private final int EXPIRATION_TIME_IN_MINUTES;
    @Value("${spring.mail.username}")
    private String fromAddress;
    @Value("${sender.name}")
    private String senderName;
    @Value("${host}")
    private String host;

    @Tag(name = "Отправка сообщения", description = "Отправляет сообщение юзеру, содержащее ссылку с подтверждением почты")
    @PostMapping
    public ResponseEntity<String> sendMessage(UserRegistrationDto userRegistrationDto) throws IOException, MessagingException {
        int code = userRegistrationDto.getEmail().hashCode();
        String toAddress = userRegistrationDto.getEmail();
        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/templates/message.html")));
        content = content.replace("[[name]]", userRegistrationDto.getFirstName())
                .replace("[[host]]", host)
                .replace("[[code]]", code + "")
                .replace("[[email]]", userRegistrationDto.getEmail());
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(fromAddress, senderName);
        helper.setText(content, true);
        helper.setTo(toAddress);
        mailSender.send(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Tag(name = "Регистрация юзера")
    @GetMapping("/verify")
    public ResponseEntity<String> verify(@RequestParam("email") String email,
                                         @RequestParam("code") int code) {
        Optional<User> optionalUser = userService.getByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            LocalDateTime linkExpirationTime = user.getPersistDateTime().plusMinutes(EXPIRATION_TIME_IN_MINUTES);
            if ((code == user.getEmail().hashCode()) &&
                    (LocalDateTime.now().isBefore(linkExpirationTime))) {
                authenticateUserAndSetSession(user);
                user.setIsEnabled(true);
                userService.persist(user);
                return new ResponseEntity<>("Вы успешно зарегистрировались!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Ссылка недействительна", HttpStatus.FORBIDDEN);
    }

    @Tag(name = "Аутентификация юзера")
    private void authenticateUserAndSetSession(User user) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
