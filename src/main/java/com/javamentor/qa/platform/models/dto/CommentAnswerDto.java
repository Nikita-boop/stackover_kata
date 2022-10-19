package com.javamentor.qa.platform.models.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "комментарий")
public class CommentAnswerDto {
    @Parameter(description = "id комментария")
    private Long id;
    @Schema(description = "id ответа к вопросу")
    private Long answerId;
    @Schema(description = "дата последнего редактирования")
    private LocalDateTime lastRedactionDate;
    @Schema(description = "дата создания комментария")
    private LocalDateTime persistDate;
    @Schema(description = "текст комментария")
    private String text;
    @Schema(description = "id пользователя")
    private Long userId;
    @Schema(description = "ссылка на аватар пользователя")
    private String imageLink;
    @Schema(description = "репутация пользователя")
    private Long reputation;
}
