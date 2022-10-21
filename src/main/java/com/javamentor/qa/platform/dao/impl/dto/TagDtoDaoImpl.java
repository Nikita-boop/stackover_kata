package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.TagDtoDao;
import com.javamentor.qa.platform.models.dto.RelatedTagDto;
import com.javamentor.qa.platform.models.dto.TrackedTagDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TagDtoDaoImpl implements TagDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<RelatedTagDto> getTop10Tags() {
        List<RelatedTagDto> tagDtos = entityManager.createQuery("""
                select new com.javamentor.qa.platform.models.dto.RelatedTagDto(
                tag.id, tag.description, sum(tag.questions.size))
                from Tag tag
                group by tag.id
                order by tag.questions.size desc
                """, RelatedTagDto.class).setMaxResults(10).getResultList();
        return tagDtos;
    }

    @Override
    public List<TrackedTagDto> getTrackedByUserId(Long id) {
        List<TrackedTagDto> TrackedTagsDtos = entityManager.createQuery("""
                select new com.javamentor.qa.platform.models.dto.TrackedTagDto(
                tag.id, tag.trackedTag.name)
                from TrackedTag tag
                where tag.user.id = :id
                group by tag.id
                order by tag.id desc
                """, TrackedTagDto.class).setParameter("id", id).getResultList();
        return TrackedTagsDtos;
    }
}
