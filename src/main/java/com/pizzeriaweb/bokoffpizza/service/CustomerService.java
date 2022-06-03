package com.pizzeriaweb.bokoffpizza.service;

import com.pizzeriaweb.bokoffpizza.entity.Customer;
import com.pizzeriaweb.bokoffpizza.entity.RegisteredUser;
import com.pizzeriaweb.bokoffpizza.repository.CustomerRepository;
import com.pizzeriaweb.bokoffpizza.rest.OrderRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer setCustomerByRequest(OrderRequestDTO request) {
        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setAddress(request.getAddress());
        customer.setPhoneNumber(request.getPhoneNumber());
        return customer;
    }

    public Customer saveCustomerByOrderRequest(OrderRequestDTO request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isLoggedIn = auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken);

        Customer customerByPhone = customerRepository.findCustomerByPhoneNumber(request.getPhoneNumber());
        if(customerByPhone != null) {
            return customerByPhone;
        }

        if(!isLoggedIn) {
            Customer customer = setCustomerByRequest(request);
            saveCustomer(customer);
            return customer;
        }

        String mail = auth.getName();
        RegisteredUser user = userDetailsServiceImpl.findUserByMail(mail);

        Customer customerByUser = user.getCustomer();
        if(customerByUser != null) {
            return customerByUser;
        }
        Customer customer = setCustomerByRequest(request);
        user.setCustomer(customer);
        saveCustomer(customer);
        return customer;
    }
}
