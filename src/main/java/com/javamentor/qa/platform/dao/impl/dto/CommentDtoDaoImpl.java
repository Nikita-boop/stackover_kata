package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.CommentDtoDao;
import com.javamentor.qa.platform.models.dto.QuestionCommentDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CommentDtoDaoImpl implements CommentDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<QuestionCommentDto> getAllQuestionCommentDtoById(long questionId) {
        return entityManager.createQuery("""
                SELECT new com.javamentor.qa.platform.models.dto.QuestionCommentDto (
                q.id,
                cq.comment.lastUpdateDateTime,
                cq.comment.persistDateTime,
                cq.comment.text,
                u.id,
                u.imageLink,
                SUM (r.count))
                FROM CommentQuestion cq
                    JOIN Question q ON q.id = cq.id
                    JOIN User u ON u.id = q.user.id
                    JOIN Reputation r ON r.author.id = cq.comment.user.id
                WHERE q.id=:questionId
                GROUP BY cq.comment.id, u.id
                """, QuestionCommentDto.class)
                .setParameter("questionId", questionId)
                .getResultList();
    }
}
