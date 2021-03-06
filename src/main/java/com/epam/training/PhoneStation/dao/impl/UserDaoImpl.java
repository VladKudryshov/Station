package com.epam.training.PhoneStation.dao.impl;

import com.epam.training.PhoneStation.dao.api.UserDao;
import com.epam.training.PhoneStation.entity.Role;
import com.epam.training.PhoneStation.entity.UserEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.junit.Assert;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class UserDaoImpl  extends AbstractDaoImpl<UserEntity> implements UserDao {

    @Override
    public UserEntity getByUserName(String login) {

        Criteria criteria = super.getSessionFactory().getCurrentSession().createCriteria(UserEntity.class);
        criteria.add(Restrictions.eq("username", login));
        criteria.setCacheable(true);

        return (UserEntity) criteria.uniqueResult();
    }

    @Override
    @Cacheable(value = "allUsers", key = "userDaoImpl")
    public List<UserEntity> getAll() {
        Query query = getSessionFactory().getCurrentSession().createQuery("from UserEntity order by id asc");
        return query.list();
    }

    @Override
    @Cacheable(value = "blockedUsers", key = "userDaoImpl")
    public List<UserEntity> getBlockedUser() {
        Criteria criteria = super.getSessionFactory().getCurrentSession().createCriteria(UserEntity.class);
        criteria.add(Restrictions.eq("role", Role.ROLE_USER_BLOCKED.name()));
        return criteria.list();
    }

}
