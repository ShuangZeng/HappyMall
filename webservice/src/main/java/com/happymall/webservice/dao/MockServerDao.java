package com.happymall.webservice.dao;

import com.happymall.webservice.domain.CardDetail;
import com.happymall.webservice.domain.MockServer;

public interface MockServerDao extends GenericDao<MockServer> {

	Boolean isCardValid(CardDetail cardDetail);

}
