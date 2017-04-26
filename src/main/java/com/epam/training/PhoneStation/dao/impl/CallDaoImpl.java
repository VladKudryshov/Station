package com.epam.training.PhoneStation.dao.impl;

import com.epam.training.PhoneStation.dao.api.CallDao;
import com.epam.training.PhoneStation.model.Call;
import org.springframework.stereotype.Repository;

@Repository
public class CallDaoImpl extends AbstractDaoImpl<Call> implements CallDao{
}
