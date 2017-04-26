package com.epam.training.PhoneStation.dao.api;

public interface AbstractDao <Type>{

    Type getById(long id);

    Type save(Type model);

    void delete(Type model);

    void update(Type model);
}
