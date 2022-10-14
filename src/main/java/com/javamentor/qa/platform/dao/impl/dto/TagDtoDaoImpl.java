package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.TagDtoDao;
import com.javamentor.qa.platform.models.dto.RelatedTagDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TagDtoDaoImpl implements TagDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<RelatedTagDto> getTopTags() {
        List<RelatedTagDto> tagDtos = entityManager.createQuery("""
                select new com.javamentor.qa.platform.models.dto.RelatedTagDto(
                tag.id, tag.description, sum(tag.questions.size))
                from Tag tag
                group by tag.id
                order by tag.questions.size desc
                """, RelatedTagDto.class).setMaxResults(10).getResultList();
        return tagDtos;
    }
}
