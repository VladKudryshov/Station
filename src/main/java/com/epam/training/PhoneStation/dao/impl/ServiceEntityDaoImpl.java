package com.epam.training.PhoneStation.dao.impl;

import com.epam.training.PhoneStation.dao.api.ServiceEntityDao;
import com.epam.training.PhoneStation.entity.ServiceEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServiceEntityDaoImpl extends AbstractDaoImpl<ServiceEntity> implements ServiceEntityDao {
    @Override
    public List<ServiceEntity> getAll() {
        Query query = getSessionFactory().getCurrentSession().createQuery("from ServiceEntity");
        return query.list();
    }

}
