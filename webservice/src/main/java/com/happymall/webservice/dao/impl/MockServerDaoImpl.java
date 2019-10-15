package com.happymall.webservice.dao.impl;

import java.util.List;

import javax.persistence.Query;
import org.springframework.stereotype.Repository;

import com.happymall.webservice.dao.MockServerDao;
import com.happymall.webservice.domain.MockServer;

@Repository
public class MockServerDaoImpl  extends GenericDaoImpl<MockServer> implements MockServerDao {
	
	public MockServerDaoImpl() {
		super.setDaoType(MockServer.class );
	}

	public MockServer findByCardNumber(String number) {
	     
		Query query = entityManager.createQuery("select m from MockServer m  where m.cardNumber =:number");
		return (MockServer) query.setParameter("number", number).getSingleResult();		     
	}
	
	@SuppressWarnings("unchecked")
	public List<MockServer> findByNameOnCard(String name){
		Query query = entityManager.createQuery("select m from MockServer m  where m.nameOnCard =:name");
		return (List<MockServer>) query.setParameter("name", name).getResultList();	
	}

}
