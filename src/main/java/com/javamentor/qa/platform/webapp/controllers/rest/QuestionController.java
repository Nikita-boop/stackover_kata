package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.service.abstracts.model.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Question", description = "The Question API")
@RestController
@RequestMapping("/api/user/question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService){
        this.questionService = questionService;
    }

    @Operation(
            summary = "Получение количества вопросов",
            description = "Получение количества активных записей в таблице question.")
    @GetMapping("/count")
    public ResponseEntity<?> getCountQuestion() {
        return new ResponseEntity<>(
                questionService.getCountQuestion(),
                HttpStatus.OK
        );
    }
}