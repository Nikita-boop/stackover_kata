package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.CommentAnswerDtoDao;
import com.javamentor.qa.platform.models.dto.CommentAnswerDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@Repository
public class CommentAnswerDtoDaoImpl implements CommentAnswerDtoDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<CommentAnswerDto> getCommentById(Long id) {
        Query query = entityManager.createQuery(
                """
                SELECT new com.javamentor.qa.platform.models.dto.CommentAnswerDto
                (ca.comment.id, ca.answer.id, ca.comment.lastUpdateDateTime,
                ca.comment.persistDateTime, ca.comment.text, ca.comment.user.id, ca.comment.user.imageLink,
                    (SELECT COALESCE(SUM(r.count), 0)
                    FROM Reputation r
                    WHERE r.author.id = ca.comment.user.id))
                FROM CommentAnswer AS ca
                JOIN Reputation AS r ON r.author.id = ca.comment.user.id
                WHERE ca.answer.id=:answerId
                """, CommentAnswerDto.class);

        query.setParameter("answerId", id);
        return query.getResultList().stream().findFirst();
    }
}
