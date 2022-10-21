package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.TagDtoDao;
import com.javamentor.qa.platform.models.dto.RelatedTagDto;
import com.javamentor.qa.platform.models.dto.TrackedTagDto;
import com.javamentor.qa.platform.service.abstracts.dto.TagDtoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagDtoServiceImpl implements TagDtoService {
    private final TagDtoDao tagDtoDao;

    public TagDtoServiceImpl(TagDtoDao tagDtoDao) {
        this.tagDtoDao = tagDtoDao;
    }

    @Override
    @Transactional
    public List<RelatedTagDto> getTop10Tags() {
        return tagDtoDao.getTop10Tags();
    }

    @Override
    public List<TrackedTagDto> getTrackedByUserId(Long id) {
        return tagDtoDao.getTrackedByUserId(id);
    }
}
