package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.models.dto.PageDto;

import java.util.Map;

public interface PaginationDtoService<T> {
    PageDto<T> getPageDtoWithParameters(Map<String, Object> parameters);
}
