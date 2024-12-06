package com.yuzee.payment.dao;

import com.yuzee.payment.model.CustomerModel;

public interface CustomerDao {

	public CustomerModel createCustomer(CustomerModel customer);

	public CustomerModel getCustomerByUserId(String userId);

}
