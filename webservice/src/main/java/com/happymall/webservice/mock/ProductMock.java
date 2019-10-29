package com.happymall.webservice.mock;

import java.util.Arrays;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.happymall.webservice.domain.Product;
import com.happymall.webservice.domain.User;

@Component
public class ProductMock {

	public static Product create() {

		Product P = new Product();
		P.setVendor(UserMock.create());
		P.setName("Football Boot");
		P.setDescription("Size 42 Adidas with red stripes");
		P.setStatus("A");
		P.setCreateDate(new Date());
		P.setPrice(500.0);
		P.setQuantity(15);

		return P;
	}

	public static Product createAndPersist() {

		Product P = new Product();
		User U = UserMock.createAndPersist();
		U.setListProduct(Arrays.asList(P));

		// AD.save(U.getListAddress().get(0));
		// CD.save(U.getListCardDetail().get(0));

		P.setVendor(U);
		P.setName("Football Boot");
		P.setDescription("Size 42 Adidas with red stripes");
		P.setStatus("A");
		P.setCreateDate(new Date());
		P.setPrice(500.0);
		P.setQuantity(15);

		// PD.save(P);
		// UD.save(U);

		return P;
	}

}
