package com.epam.training.PhoneStation.dao.impl;

import com.epam.training.PhoneStation.dao.api.CallDao;
import com.epam.training.PhoneStation.entity.CallEntity;
import com.epam.training.PhoneStation.entity.UserEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CallDaoImpl extends AbstractDaoImpl<CallEntity> implements CallDao{

    @Override
    public List<CallEntity> getAll() {
        Query query = getSessionFactory().getCurrentSession().createQuery("from CallEntity");
        return query.list();
    }

}
