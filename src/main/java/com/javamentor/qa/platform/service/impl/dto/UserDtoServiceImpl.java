package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.UserDtoDao;
import com.javamentor.qa.platform.dao.abstracts.repository.ReadWriteDao;
import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.service.abstracts.dto.UserDtoService;
import com.javamentor.qa.platform.service.impl.repository.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDtoServiceImpl extends ReadWriteServiceImpl<UserDto, Long> implements UserDtoService {
    private final UserDtoDao userDtoDao;

    public UserDtoServiceImpl(ReadWriteDao<UserDto, Long> readWriteDao, UserDtoDao userDtoDao) {
        super(readWriteDao);
        this.userDtoDao = userDtoDao;
    }

    @Transactional
    @Override
    public Optional<UserDto> getById(Long id) {

        return userDtoDao.getById(id);
    }

}