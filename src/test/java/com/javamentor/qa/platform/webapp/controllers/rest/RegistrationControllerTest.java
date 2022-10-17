package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.UserRegistrationDto;
import com.javamentor.qa.platform.webapp.configs.JmApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = JmApplication.class)
class RegistrationControllerTest {

    private MockMvc mvc;
    private UserRegistrationDto userRegistrationDto;

    @Autowired
    private WebApplicationContext wac;

    @BeforeEach
    public void setup(){
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void sendMessage() throws Exception {
        userRegistrationDto = new UserRegistrationDto("Ivan", "Petrov",
                "selezand@mail.ru", "a123456");

        mvc.perform(post("/api/user/registration", userRegistrationDto))
            .andExpect(status().isOk());
    }

    @Test
    void verify() {

    }
}