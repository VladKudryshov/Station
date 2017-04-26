package com.epam.training.PhoneStation.dao.impl;

import com.epam.training.PhoneStation.dao.api.ServiceModelDao;
import com.epam.training.PhoneStation.model.ServiceModel;
import com.epam.training.PhoneStation.model.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServiceModelDaoImpl extends AbstractDaoImpl<ServiceModel> implements ServiceModelDao {
    @Override
    public List<ServiceModel> getAll() {
        Query query = getSessionFactory().getCurrentSession().createQuery("from ServiceModel");
        return query.list();
    }

}
