package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.CommentAnswerDao;
import com.javamentor.qa.platform.exception.AnswerException;
import com.javamentor.qa.platform.models.entity.Comment;
import com.javamentor.qa.platform.models.entity.CommentType;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.models.entity.question.answer.CommentAnswer;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.model.AnswerService;
import com.javamentor.qa.platform.service.abstracts.model.CommentAnswerService;
import com.javamentor.qa.platform.service.impl.repository.ReadWriteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentAnswerServiceImpl extends ReadWriteServiceImpl<CommentAnswer, Long> implements CommentAnswerService {

    private final CommentAnswerDao commentAnswerDao;
    private final AnswerService answerService;

    @Autowired
    public CommentAnswerServiceImpl(CommentAnswerDao commentAnswerDao, AnswerService answerService) {
        super(commentAnswerDao);
        this.commentAnswerDao = commentAnswerDao;
        this.answerService = answerService;
    }

    @Override
    public void addCommentToAnswer(Long answerId, String textComment, User user) {
        Optional<Answer> answer = answerService.getById(answerId);
        if (answer.isEmpty()) {
            throw new AnswerException("Ответа не существует");
        }
        CommentAnswer commentAnswer = new CommentAnswer();
        commentAnswer.setComment(new Comment(CommentType.ANSWER));
        commentAnswer.setAnswer(answer.get());
        commentAnswer.setText(textComment);
        commentAnswer.setUser(user);
        commentAnswerDao.persist(commentAnswer);

    }
}