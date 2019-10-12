package com.happymall.webservice.dao;

import java.util.List;
import java.util.UUID;

public interface GenericDao<T> {
	/**
     * Method that returns the number of entries from a table that meet some
     * criteria (where clause params)
     * @param params
     *  sql parameters
     * @return the number of records meeting the criteria
     */

    void save(T t);

    void delete(UUID id);

    T findOne(UUID id);

    T update(T t);   
    
    List<T> findAll();

    
}
