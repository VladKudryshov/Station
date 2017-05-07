package com.epam.training.PhoneStation.dao.impl;

import com.epam.training.PhoneStation.dao.api.AbstractDao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractDaoImpl<Type> implements AbstractDao<Type>{

    private Class actualClass = this.getClass();
    private ParameterizedType type = (ParameterizedType)actualClass.getGenericSuperclass();
    private Class parameter = (Class)type.getActualTypeArguments()[0];

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public Type getById(long id) {

        return (Type) sessionFactory.getCurrentSession().get(parameter, id);
    }

    @Override
    public Type save(Type model) {
        sessionFactory.getCurrentSession().saveOrUpdate(model);
        return model;
    }

    @Override
    public void delete(Type model) {
        sessionFactory.getCurrentSession().delete(model);
    }

    @Override
    public void update(Type model) {
        sessionFactory.getCurrentSession().saveOrUpdate(model);
    }
}
