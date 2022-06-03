package com.pizzeriaweb.bokoffpizza.service;

import com.pizzeriaweb.bokoffpizza.entity.Customer;
import com.pizzeriaweb.bokoffpizza.entity.RegisteredUser;
import com.pizzeriaweb.bokoffpizza.repository.CustomerRepository;
import com.pizzeriaweb.bokoffpizza.repository.RegisteredUserRepository;
import com.pizzeriaweb.bokoffpizza.rest.OrderRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @MockBean
    CustomerRepository customerRepository;

    @MockBean
    RegisteredUserRepository registeredUserRepository;



    @Test
    void saveCustomer() {
        Customer customer = new Customer();
        customerService.saveCustomer(customer);
        Mockito.verify(customerRepository, Mockito.times(1)).save(customer);
    }

    @Test
    void setCustomerByRequest() {
        OrderRequestDTO request = new OrderRequestDTO();
        request.setAddress("address");
        request.setFirstName("John");
        request.setPhoneNumber("89999999999");

        Customer customer = customerService.setCustomerByRequest(request);
        assertThat(customer.getFirstName(), equalTo(request.getFirstName()));
        assertThat(customer.getPhoneNumber(), equalTo(request.getPhoneNumber()));
        assertThat(customer.getAddress(), equalTo(request.getAddress()));
    }
}