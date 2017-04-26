package com.epam.training.PhoneStation.dao.impl;

import com.epam.training.PhoneStation.dao.api.UserDao;
import com.epam.training.PhoneStation.model.Role;
import com.epam.training.PhoneStation.model.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository

public class UserDaoImpl  extends AbstractDaoImpl<User> implements UserDao {

    @Override
    public User getByLogin(String login) {

        Criteria criteria = super.getSessionFactory().getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("username", login));

        return (User) criteria.uniqueResult();
    }

    @Override
    public List<User> getAll() {
        Query query = getSessionFactory().getCurrentSession().createQuery("from User");
        return query.list();
    }

    @Override
    public List<User> getBlockedUser() {
        Criteria criteria = super.getSessionFactory().getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("role", Role.ROLE_USER.name()));
        return criteria.list();
    }

}
