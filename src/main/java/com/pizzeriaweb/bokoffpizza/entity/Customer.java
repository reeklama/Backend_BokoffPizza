package com.pizzeriaweb.bokoffpizza.entity;

import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customers")
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String first_name;
    private String phone_number;
    private String address;

    @OneToOne (mappedBy="customer")
    private RegisteredUser registeredUser;

    @OneToMany (mappedBy="customer")
    private Set<Order> orders;

    public Customer() {
    }

    public RegisteredUser getRegisteredUser() {
        return registeredUser;
    }

    public void setRegisteredUser(RegisteredUser registeredUser) {
        this.registeredUser = registeredUser;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
