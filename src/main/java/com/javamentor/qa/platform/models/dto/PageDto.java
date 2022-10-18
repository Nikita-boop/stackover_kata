package com.javamentor.qa.platform.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PageDto<T> implements Serializable {
    private Integer currentPageNumber;
    private Integer totalPageCount;
    private Integer totalResultCount;
    private List<T> items;
    private Integer itemsOnPage;
}
