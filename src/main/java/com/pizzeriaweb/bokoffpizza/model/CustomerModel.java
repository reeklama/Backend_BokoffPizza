package com.pizzeriaweb.bokoffpizza.model;

import com.pizzeriaweb.bokoffpizza.entity.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerModel {
    private Long id;

    private String first_name;

    private String phone_number;

    private String address;

    public static CustomerModel toModel (Customer customer) {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setId(customer.getId());
        customerModel.setFirst_name(customer.getFirstName());
        customerModel.setPhone_number(customer.getPhoneNumber());
        customerModel.setAddress(customer.getAddress());
        return customerModel;
    }
}
