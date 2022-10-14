package com.javamentor.qa.platform.webapp.configs.initdb;

import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.question.Tag;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.models.entity.user.Role;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.models.entity.user.reputation.Reputation;
import com.javamentor.qa.platform.service.abstracts.model.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestDataInitService {

    final
    UserService userService;

    public TestDataInitService(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    public void createEntity() {
        createUsers();
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    List<User> users = new ArrayList<>();

    private void createUsers() {
        if (userService.getByEmail("durov@example.com").isEmpty() && userService.getByEmail("admin@example.com").isEmpty()) {
            User user = new User("Павел Дуров", "durov@example.com",
                    passwordEncoder().encode("password"));
            Role role_user = new Role("ROLE_USER");

            User admin = new User("Стив Возняк", "admin@example.com",
                    passwordEncoder().encode("password"));
            Role role_admin = new Role("ROLE_ADMIN");

            user.setRole(role_user);
            admin.setRole(role_admin);
            userService.persistAll(user, admin);
        }
    }

    List<Tag> tags = new ArrayList<>();

    private void createTags() {

    }

    List<Question> questions = new ArrayList<>();

    private void createQuestions() {

    }

    List<Answer> answers = new ArrayList<>();

    private void createAnswers() {

    }

    List<Reputation> reputations = new ArrayList<>();

    private void createReputations() {

    }
}
