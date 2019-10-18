package com.example.HappyMall.repositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.HappyMall.repository.ShoppingCartRepository;

@Repository
@Transactional
public class ShoppingCartRepositoryImpl implements ShoppingCartRepository {

	@PersistenceContext
    private EntityManager em;

	@Override
	public void updateMoneyByOrdersId(int orderId) {
		// TODO Auto-generated method stub
		//ordersRepository.updateMoneyByOrdersId(orderId);
		String queryStr = String.format("update Orders a " + 
				" set subTotal = (select sum(b.price * b.quantity) from OrderLine b where b.orders = a.id)," + 
				"	tax = (select sum(b.price * b.quantity) * 0.07 from OrderLine b where b.orders = a.id)," + 
				"    a.total = (select sum(b.price * b.quantity) * 1.07 from OrderLine b where b.orders = a.id)," + 
				"    serviceFee = (select sum(b.price * b.quantity) * 0.25 from OrderLine b where b.orders = a.id) " + 
				" where a.id = %d",orderId);
		
        Query query = em.createQuery(queryStr);
        
        query.executeUpdate();
	}

	@Override
	public void deleteByOrdersIdAndProductId(int ordersId, int productId) {
		// TODO Auto-generated method stub
		String queryStr = String.format("delete from OrderLine where orders = %d and product = %d", ordersId, productId);
		
        Query query = em.createQuery(queryStr);
        
        query.executeUpdate();
	}

}
