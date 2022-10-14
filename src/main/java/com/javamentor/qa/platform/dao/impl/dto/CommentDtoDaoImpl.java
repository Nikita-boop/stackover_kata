package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.CommentDtoDao;
import com.javamentor.qa.platform.models.dto.QuestionCommentDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CommentDtoDaoImpl implements CommentDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<QuestionCommentDto> getAllQuestionCommentDtoById(long questionId) {

        Query query = entityManager.createQuery("""
                      select new com.javamentor.qa.platform.models.dto.QuestionCommentDto (
                      q.id, cq.comment.lastUpdateDateTime, cq.comment.persistDateTime,
                      cq.comment.text, u.id, u.imageLink, sum(r.count))
                      from Question q
                      join CommentQuestion cq on cq.question = q
                      join User u on u = q.user
                      join Reputation r on r.author = u
                                     
                      where q.id=:id
                      
                      group by cq.comment.id, u.id
                      """, QuestionCommentDto.class).setParameter("id", questionId);

        return query.getResultList();
    }

}
