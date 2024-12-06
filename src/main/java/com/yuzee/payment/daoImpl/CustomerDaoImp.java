package com.yuzee.payment.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.yuzee.payment.dao.CustomerDao;
import com.yuzee.payment.model.CustomerModel;
import com.yuzee.payment.repository.CustomerRepostory;

@Service
public class CustomerDaoImp implements CustomerDao {
	@Autowired
	private CustomerRepostory customerRepository;
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public CustomerModel createCustomer(CustomerModel customer) {
		return customerRepository.save(customer);
	}

	@Override
	public CustomerModel getCustomerByUserId(String userId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(userId));
		return mongoTemplate.findOne(query, CustomerModel.class);
	}

}
