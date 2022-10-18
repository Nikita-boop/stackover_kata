package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.PageDtoDao;
import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.service.abstracts.dto.PaginationDtoService;

import java.util.List;
import java.util.Map;

public abstract class PaginationDtoServiceImpl<T> implements PaginationDtoService {
    private final PageDtoDao<T> pageDtoDao;

    public PaginationDtoServiceImpl(PageDtoDao<T> pageDtoDao) {
        this.pageDtoDao = pageDtoDao;
    }

    @Override
    public PageDto getPageDtoWithParameters(Map parameters) {
        Integer currentPageNumber = (Integer) parameters.get("currentPageNumber");
        Integer itemsOnPage = (Integer) parameters.get("itemsOnPage");
        Integer totalResultCount = pageDtoDao.getTotalResultCount(parameters);
        Integer totalPageCounter = (totalResultCount % itemsOnPage == 0) ?
                totalResultCount / itemsOnPage: (totalResultCount / itemsOnPage) + 1;
        List<T> items = pageDtoDao.getItems(parameters);
        return new PageDto<T>(currentPageNumber, totalPageCounter, totalResultCount, items, itemsOnPage);
    }
}
