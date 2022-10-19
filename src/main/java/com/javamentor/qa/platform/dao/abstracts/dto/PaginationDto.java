package com.javamentor.qa.platform.dao.abstracts.dto;

import java.util.List;
import java.util.Map;

public interface PaginationDto<T> {
    List<T> getItems(Map<String, Object> params);
    int getCountOfAllItems(Map<String, Object> params);
}
