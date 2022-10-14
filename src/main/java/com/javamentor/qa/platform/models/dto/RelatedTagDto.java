package com.javamentor.qa.platform.models.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Related Tag.")
public class RelatedTagDto {
    @Parameter(description = "ID тега.")
    private Long id;
    @Schema(description = "Описание тега.")
    private String title;
    @Schema(description = "Количество вопросов в которых использовался тег.")
    private Long countQuestion;
}
