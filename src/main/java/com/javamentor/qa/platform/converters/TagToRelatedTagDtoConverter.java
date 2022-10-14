package com.javamentor.qa.platform.converters;

import com.javamentor.qa.platform.models.dto.RelatedTagDto;
import com.javamentor.qa.platform.models.entity.question.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class TagToRelatedTagDtoConverter {
    public static final TagToRelatedTagDtoConverter CONVERT = Mappers.getMapper(TagToRelatedTagDtoConverter.class);

    @Mapping(target = "id", source = "tag.id")
    @Mapping(target = "title", source = "tag.description")
    @Mapping(target = "countQuestion", source="count")
    public abstract RelatedTagDto tagToRelatedTagDto(Tag tag, Long count);

    public abstract List<RelatedTagDto> tagListToRelatedTagDtoList(List<Tag> tagList);
}
