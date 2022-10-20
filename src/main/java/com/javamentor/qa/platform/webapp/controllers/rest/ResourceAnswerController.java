package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.service.abstracts.model.AnswerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.javamentor.qa.platform.models.dto.CommentAnswerDto;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.dto.CommentAnswerDtoService;
import com.javamentor.qa.platform.service.abstracts.model.CommentAnswerService;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/api/user/question/{questionId}/answer")
@Tag(name = "Answer", description = "The Answer API")
public class ResourceAnswerController {

    private final CommentAnswerDtoService commentAnswerDtoService;
    private final CommentAnswerService commentAnswerService;
    private final AnswerService answerService;

    public ResourceAnswerController(CommentAnswerDtoService commentAnswerDtoService,
                                    CommentAnswerService commentAnswerService, AnswerService answerService) {
        this.commentAnswerDtoService = commentAnswerDtoService;
        this.commentAnswerService = commentAnswerService;
        this.answerService = answerService;
    }

    @Operation(summary = "Добавление комментария к ответу на вопрос")
    @ApiResponse(responseCode = "200", description = "успешное выполнение",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CommentAnswerDto.class)))
    @ApiResponse(responseCode = "400", description = "комментарий не добавлен")
    @PostMapping("/{answerId}/comment")
    public ResponseEntity<CommentAnswerDto> postCommentToAnswer(
            @Parameter(description = "id ответа на вопрос", required = true) @PathVariable("answerId") Long answerId,
            @NotNull @NotEmpty @org.springframework.web.bind.annotation.RequestBody String comment,
            @AuthenticationPrincipal User user) {
        if (answerService.getById(answerId).isPresent()) {
            commentAnswerService.addCommentToAnswer(answerId, comment, user);
            return new ResponseEntity<>(commentAnswerDtoService.getCommentById(answerId).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Удаление ответа на вопрос по его id")
    @ApiResponse(responseCode = "200", description = "успешное выполнение",
            content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "ответ не найден")
    @DeleteMapping("/{answerId}")
    public ResponseEntity<?> deleteAnswerById(@Parameter(description = "id по которому нужно найти ответ", required = true)
                                              @PathVariable("answerId") Long answerId) {
        if (answerService.getById(answerId).isPresent()) {
            answerService.deleteById(answerId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}