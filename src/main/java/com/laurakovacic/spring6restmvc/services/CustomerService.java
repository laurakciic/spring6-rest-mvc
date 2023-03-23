package com.laurakovacic.spring6restmvc.services;

import com.laurakovacic.spring6restmvc.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<Customer> listCustomers();
    Customer getCustomerById(UUID id);
    Customer saveNewCustomer(Customer customer);
}
