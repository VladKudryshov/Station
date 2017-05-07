package com.epam.training.PhoneStation.dao.impl;

import com.epam.training.PhoneStation.dao.api.ContractDao;
import com.epam.training.PhoneStation.entity.ContractEntity;
import com.epam.training.PhoneStation.entity.UserEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContractDaoImpl extends AbstractDaoImpl<ContractEntity> implements ContractDao{
    @Override
    public List<ContractEntity> getAll() {
        Query query = getSessionFactory().getCurrentSession().createQuery("from ContractEntity order by id asc");
        return query.list();
    }
}
