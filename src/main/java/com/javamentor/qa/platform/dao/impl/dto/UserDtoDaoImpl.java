package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.UserDtoDao;
import com.javamentor.qa.platform.dao.impl.repository.ReadWriteDaoImpl;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.dto.UserDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.Optional;

@Repository
public class UserDtoDaoImpl extends ReadWriteDaoImpl<UserDto, Long> implements UserDtoDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Optional<UserDto> getById(Long id) {

        Query query = entityManager.createQuery("""
                      select new com.javamentor.qa.platform.models.dto.UserDto (
                      u.id, u.email, u.fullName,
                      u.imageLink, u.city, r.count, u.persistDateTime,
                      (count(distinct voa.id) + count(distinct voq.id)
                ))
                      from User u
                      join Reputation r on r.author = u
                      join VoteAnswer voa on voa.user = u
                      join VoteQuestion voq on voq.user = u
                                     
                      where u.id=:id and voa.user.id = u.id and voq.user.id = u.id
                      group by u.id, r.count
                      """, UserDto.class).setParameter("id", id);

        return SingleResultUtil.getSingleResultOrNull(query);

    }
}