package com.pizzeriaweb.bokoffpizza.service;

import com.pizzeriaweb.bokoffpizza.entity.Customer;
import com.pizzeriaweb.bokoffpizza.entity.RegisteredUser;
import com.pizzeriaweb.bokoffpizza.repository.CustomerRepository;
import com.pizzeriaweb.bokoffpizza.rest.OrderRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void saveCustomerByUserIfNotExists(RegisteredUser user, OrderRequestDTO request) {
        Customer customer = user.getCustomer();
        if(customer == null) {
            customer = new Customer();
            customer.setFirst_name(request.getFirst_name());
            customer.setAddress(request.getAddress());
            customer.setPhone_number(request.getPhone_number());
            customerRepository.save(customer);
            user.setCustomer(customer);
        }
    }
}
