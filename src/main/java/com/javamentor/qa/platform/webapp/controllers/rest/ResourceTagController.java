package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.RelatedTagDto;
import com.javamentor.qa.platform.service.abstracts.dto.TagDtoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Контроллер тегов", description = "Контроллер для работы с тегами")
@RestController
@RequestMapping("/api/user/tag")
public class ResourceTagController {

    private final TagDtoService tagDtoService;

    public ResourceTagController(TagDtoService tagDtoService) {
        this.tagDtoService = tagDtoService;
    }

    @Operation(summary = "Топ 10 тегов", description = "Топ 10 тегов в вопросах")
    @ApiResponse(responseCode = "200",
            description = "10 самых используемых тегов.",
            content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RelatedTagDto.class)))
            )
    @GetMapping("/related")
    public ResponseEntity<List<RelatedTagDto>> getTop10Tags() {
        return new ResponseEntity<>(tagDtoService.getTop10Tags(), HttpStatus.OK);
    }
}
