package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.QuestionCommentDto;
import com.javamentor.qa.platform.service.abstracts.dto.CommentDtoService;
import com.javamentor.qa.platform.service.abstracts.model.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Question", description = "The Question API")
@RestController
@RequestMapping("/api/user/question")
public class QuestionController {

    private final QuestionService questionService;
    private final CommentDtoService commentDtoService;

    public QuestionController(QuestionService questionService, CommentDtoService commentDtoService) {
        this.questionService = questionService;
        this.commentDtoService = commentDtoService;
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

    @Operation(
            summary = "Получение комментариев к вопросу",
            description = "Получение всех комментариев к вопросу")
    @GetMapping("/{id}/comment")
    public ResponseEntity<List<QuestionCommentDto>> getAllCommentsOnQuestion(@PathVariable("id") Long questionId) {
        if (questionService.existsById(questionId)) {
            List<QuestionCommentDto> commentDtoList = commentDtoService.getAllQuestionCommentDtoById(questionId);
            return commentDtoList.isEmpty()
                    ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                    : new ResponseEntity<>(commentDtoList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}