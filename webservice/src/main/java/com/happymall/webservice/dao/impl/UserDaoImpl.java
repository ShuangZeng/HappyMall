package com.happymall.webservice.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.happymall.webservice.dao.UserDao;
import com.happymall.webservice.domain.User;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

	public UserDaoImpl() {
		super.setDaoType(User.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllVendors() {

		Query query = entityManager.createQuery("select v from User v join v.role r where r.role =: role");
		return (List<User>) query.setParameter("role", "Vendor").getResultList();
	}

	@Override
	public User findByEmail(String email) {

		Query query = entityManager.createQuery("select v from User v  where v.email =: email");
		return (User) query.setParameter("email", email).getSingleResult();
	}

	@Override
	public User findByPhone(String phone) {

		Query query = entityManager.createQuery("select v from User v  where v.phone =: phone");
		return (User) query.setParameter("phone", phone).getSingleResult();
	}

}
