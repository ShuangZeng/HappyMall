package com.happymall.webservice.dao.impl;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.happymall.webservice.dao.GenericDao;


@SuppressWarnings("unchecked")
@Repository
public abstract class GenericDaoImpl<T> implements GenericDao<T> {

	@PersistenceContext
    protected EntityManager entityManager;

    protected Class<T> daoType;

	public void setDaoType(Class<T> type) {
			daoType = type;
	}
   
    @Override
    public void save( T entity ){
        entityManager.persist( entity );
     }

    public void delete( T entity ){
        entityManager.remove( entity );
     }

	@Override
	public void delete(UUID id) {
        T entity = findOne( id );
        delete( entity );  
    }

	@Override
	public T findOne( UUID id ){
	    return (T) entityManager.find( daoType, id );
	 }
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(){
		      return entityManager.createQuery( "from " + daoType.getName() )
		       .getResultList();
		   }
	
	@Override
	public T update( T entity ){
	      return entityManager.merge( entity );
	   }


 }
