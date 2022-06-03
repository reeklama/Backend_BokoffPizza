package com.pizzeriaweb.bokoffpizza.service;

import com.pizzeriaweb.bokoffpizza.entity.Customer;
import com.pizzeriaweb.bokoffpizza.entity.RegisteredUser;
import com.pizzeriaweb.bokoffpizza.entity.Role;
import com.pizzeriaweb.bokoffpizza.exception.RegisteredUserNotFoundException;
import com.pizzeriaweb.bokoffpizza.exception.RoleNotFoundException;
import com.pizzeriaweb.bokoffpizza.repository.RegisteredUserRepository;
import com.pizzeriaweb.bokoffpizza.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserDetailsServiceImplTest {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @MockBean
    RegisteredUserRepository registeredUserRepository;

    @MockBean
    RoleRepository roleRepository;

    @Test
    void loadUserByUsername() {
        RegisteredUser user = new RegisteredUser();
        user.setMail("John@gmail.com");
        user.setPassword("123456");
        user.setRole(new Role(1L, "ROLE_USER"));
        Mockito.doReturn(user)
                .when(registeredUserRepository)
                .findByMail(user.getMail());

        UserDetails userTest = userDetailsService.loadUserByUsername("John@gmail.com");
        assertThat(userTest, notNullValue());

        Mockito.verify(registeredUserRepository, Mockito.times(1)).findByMail("John@gmail.com");
    }

    @Test
    void findUserByMail() {
        Mockito.doReturn(new RegisteredUser())
                .when(registeredUserRepository)
                .findByMail("John@gmail.com");
        RegisteredUser user = userDetailsService.findUserByMail("John@gmail.com");
        assertThat(user, notNullValue());
        Mockito.verify(registeredUserRepository, Mockito.times(1)).findByMail("John@gmail.com");
    }

    @Test()
    void findUserByMailFail() {
        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.findUserByMail("John@gmail.com");
        });
        Mockito.verify(registeredUserRepository, Mockito.times(1)).findByMail("John@gmail.com");
    }

    @Test
    void findCustomerByUserMail() {
        RegisteredUser user = new RegisteredUser();
        Customer customer = new Customer();
        user.setCustomer(customer);
        user.setMail("John@gmail.com");

        Mockito.doReturn(user)
                .when(registeredUserRepository)
                .findByMail("John@gmail.com");
        Customer customerTest = userDetailsService.findCustomerByUserMail("John@gmail.com");
        assertThat(customerTest, notNullValue());
    }

    @Test
    void findCustomerByUserMailFail() {
        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.findCustomerByUserMail("John@gmail.com");
        });
        Mockito.verify(registeredUserRepository, Mockito.times(1)).findByMail("John@gmail.com");
    }

    @Test
    void setRole() throws Exception {
        RegisteredUser user = new RegisteredUser();
        user.setMail("John@gmail.com");
        user.setRole(new Role(1L, "ROLE_USER"));

        Mockito.doReturn(user)
                .when(registeredUserRepository)
                .findByMail("John@gmail.com");

        Role role = new Role(2L, "ROLE_SYSADMIN");

        Mockito.doReturn(role)
                .when(roleRepository)
                .findByName("SYSADMIN");

        userDetailsService.setRole("John@gmail.com", "SYSADMIN");
        assertThat(user.getRole().getName(), is(equalTo(role.getName())));
    }

    @Test
    void setRoleRoleNotFound() throws Exception {
        assertThrows(RoleNotFoundException.class, () -> {
            userDetailsService.setRole("John@gmail.com", "SYSADMIN");
        });
    }

    @Test
    void setRoleUserNotFound() throws Exception {
        Role role = new Role(2L, "ROLE_SYSADMIN");

        Mockito.doReturn(role)
                .when(roleRepository)
                .findByName("SYSADMIN");

        assertThrows(RegisteredUserNotFoundException.class, () -> {
            userDetailsService.setRole("John@gmail.com", "SYSADMIN");
        });
    }

    @Test
    void setRoleUserHaveSameRole() throws Exception {
        RegisteredUser user = new RegisteredUser();
        user.setMail("John@gmail.com");
        user.setRole(new Role(2L, "ROLE_SYSADMIN"));

        String oldRole = user.getRole().getName();

        Mockito.doReturn(user)
                .when(registeredUserRepository)
                .findByMail("John@gmail.com");

        Role role = new Role(2L, "ROLE_SYSADMIN");

        Mockito.doReturn(role)
                .when(roleRepository)
                .findByName("SYSADMIN");

        userDetailsService.setRole("John@gmail.com", "SYSADMIN");
        assertThat(user.getRole().getName(), equalTo(oldRole));
    }

    @Test
    void updateUserPassword() {
        RegisteredUser user = new RegisteredUser();
        String password = "123456";
        userDetailsService.updateUserPassword(user, password);
        assertThat(user.getPassword(), equalTo(password));
        Mockito.verify(registeredUserRepository, Mockito.times(1)).save(user);
    }

    @Test
    void deleteUser() {
        RegisteredUser user = new RegisteredUser();
        userDetailsService.deleteUser(user);
        Mockito.verify(registeredUserRepository, Mockito.times(1)).delete(user);
    }

    @Test
    void saveUser() {
        RegisteredUser user = new RegisteredUser();
        user.setMail("John@gmail.com");
        userDetailsService.saveUser(user);
        Mockito.verify(registeredUserRepository, Mockito.times(1)).save(user);
    }

    @Test
    void findAll() {
        userDetailsService.findAll();
        Mockito.verify(registeredUserRepository, Mockito.times(1)).findAll();
    }
}