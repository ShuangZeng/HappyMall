package com.happymall.webservice.dao.impl;

import javax.persistence.Query;
import org.springframework.stereotype.Repository;

import com.happymall.webservice.dao.MockServerDao;
import com.happymall.webservice.domain.MockServer;

@Repository
public class MockServerDaoImpl  extends GenericDaoImpl<MockServer> implements MockServerDao {
	
	public MockServerDaoImpl() {
		super.setDaoType(MockServer.class );
		}

	public MockServer findByCardDetails(Integer number) {
	     
		Query query = entityManager.createQuery("select m from MockServer m  where m.memberNumber =:number");
		return (MockServer) query.setParameter("number", number).getSingleResult();
			     

	}

}
