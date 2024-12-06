package com.yuzee.payment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.yuzee.payment.model.CustomerModel;

@Repository
public interface CustomerRepostory extends MongoRepository<CustomerModel,String>{

}
