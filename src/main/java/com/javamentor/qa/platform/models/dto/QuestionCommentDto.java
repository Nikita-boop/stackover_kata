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
public class QuestionCommentDto {
    @Parameter(description = "id вопроса")
    private Long questionId;
    @Schema(description = "дата последнего редактирования комментария")
    private LocalDateTime lastRedactionDate;
    @Schema(description = "дата создания комментария")
    private LocalDateTime persistDate;
    @Schema(description = "текст комментария")
    private String text;
    @Schema(description = "id пользователя")
    private Long userId;
    @Schema(description = "ссылка на аватар пользователя")
    private String imageLink;
    @Schema(description = "репутация за комментарий")
    private Long reputation;
}
