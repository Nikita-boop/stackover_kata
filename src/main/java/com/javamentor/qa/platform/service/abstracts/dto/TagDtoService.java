package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.models.dto.RelatedTagDto;
import com.javamentor.qa.platform.models.dto.TrackedTagDto;

import java.util.List;

public interface TagDtoService {

    List<RelatedTagDto> getTop10Tags();

    List<TrackedTagDto> getTrackedByUserId(Long id);
}
