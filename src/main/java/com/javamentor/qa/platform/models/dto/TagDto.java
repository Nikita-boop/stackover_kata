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
@Schema(description = "тeг")
public class TagDto {
    @Parameter(description = "id тeга")
    private Long id;
    @Schema(description = "название тeга")
    private String name;
    @Schema(description = "описание тeга")
    private String description;
}
