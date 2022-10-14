package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.CommentDtoDao;
import com.javamentor.qa.platform.models.dto.QuestionCommentDto;
import com.javamentor.qa.platform.service.abstracts.dto.CommentDtoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentDtoServiceImpl implements CommentDtoService {
    private final CommentDtoDao commentDtoDao;

    public CommentDtoServiceImpl(CommentDtoDao commentDtoDao) {
        this.commentDtoDao = commentDtoDao;
    }

    @Transactional
    @Override
    public List<QuestionCommentDto> getAllQuestionCommentDtoById(Long id) {
        return commentDtoDao.getAllQuestionCommentDtoById(id);
    }
}
