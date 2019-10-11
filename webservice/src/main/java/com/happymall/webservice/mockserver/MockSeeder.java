package com.happymall.webservice.mockserver;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import com.happymall.webservice.dao.MockServerDao;
import com.happymall.webservice.domain.MockServer;
import com.opencsv.CSVReader;

@Component
@Transactional
public class MockSeeder {
	
	@PersistenceContext
    protected EntityManager entityManager;
	
	@Autowired
	MockServerDao mockServerDao;
	
	@EventListener
	public void seed(ContextRefreshedEvent event) {
	    seedMockServerTable();
	}
	
	public void seedMockServerTable() {
		
		List<MockServer> data = mockServerDao.findAll();
		if(data.isEmpty()) {
			
			MockServer mock;
			CSVReader csvReader;
			try {
				File file = ResourceUtils.getFile("classpath:seed.csv");
				
	            Reader reader = Files.newBufferedReader(file.toPath());
	            csvReader = new CSVReader(reader);
	            String[] nextRecord;
	            while ((nextRecord = csvReader.readNext()) != null) {

	    			mock = new MockServer();
	    			mock.setCardNumber(nextRecord[0]);
	    			mock.setNameOnCard(nextRecord[1]);
	    			mock.setValue(Double.parseDouble(nextRecord[2]));
	    			mock.setRemainingValue(Double.parseDouble(nextRecord[3]));
	    			mock.setIssuedDate(new Date());
	    			mock.setExpiredDate(new Date());
	    			mock.setType(nextRecord[4]);
	    			mock.setActiveInd(nextRecord[5]);
	    			
	    			mockServerDao.save(mock);
	                
	            }
		        csvReader.close();
		    }catch(IOException e) {
		    	System.out.println("Cannot read file");
		    }
			
			data = mockServerDao.findAll();
			data.forEach(e -> System.out.println(e.getId()));
		
		
		}
	}

}
