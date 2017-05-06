package com.epam.training.PhoneStation.dao.impl;

import com.epam.training.PhoneStation.dao.api.PaymentDao;
import com.epam.training.PhoneStation.entity.PaymentEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentDaoImpl extends AbstractDaoImpl<PaymentEntity> implements PaymentDao {
    @Override
    public List<PaymentEntity> getAll() {
        Query query = getSessionFactory().getCurrentSession().createQuery("from PaymentEntity order by paid asc");
        return query.list();
    }

}
