package com.example.MovieBooking.ServiceTest;


import com.example.MovieBooking.Repository.CustomerRepo;
import com.example.MovieBooking.Service.CustomerService;
import com.example.MovieBooking.model.Customer;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;


import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepo customerRepo;


    @InjectMocks
    private CustomerService customerService;


    @Test
    public void NewCustomerTesting()
    {
        Customer customer=new Customer();
        customer.setName("Anvitha");
        customer.setEmailid("Karnataka@gmail.com");
        given(customerRepo.save(ArgumentMatchers.any(Customer.class))).willReturn(customer);
        Customer newCustomer=customerService.addCustomer(customer);
        assertThat(newCustomer).isSameAs(customer);
        verify(customerRepo).save(customer);

    }

    @Test
    public void UpdateCustomerTesting()
    {
        Customer oldCustomer=new Customer();
        oldCustomer.setId(1);
        oldCustomer.setName("Mangalore");
        oldCustomer.setEmailid("Karnataka@gmail.com");
        Customer newCustomer=new Customer();
        newCustomer.setId(1);
        newCustomer.setName("Bangalore");
        newCustomer.setEmailid("Karnata@gmail.com");
        given(customerRepo.findById(oldCustomer.getId())).willReturn(Optional.of(oldCustomer));
        customerService.updateCustomer(oldCustomer.getId(),newCustomer);
        verify(customerRepo).save(newCustomer);
        verify(customerRepo).findById(oldCustomer.getId());
    }


    @Test
    public void GetCustomerTesting()
    {
        List<Customer> customerList= new ArrayList<>();
        customerList.add(new Customer());
        given(customerRepo.findAll()).willReturn(customerList);
        List<Customer> expected=customerService.getCustomer();
        assertEquals(customerList,expected);
        verify(customerRepo).findAll();
    }

    @Test
    public void DeleteCustomerTesting()
    {
        Customer customer=new Customer();
        customer.setId(1);
        customer.setName("mangalore");
        customer.setEmailid("Karnataka@gmail.com");
        when(customerRepo.findById(customer.getId())).thenReturn(Optional.of(customer));
        customerService.deleteCustomer(customer.getId());
        verify(customerRepo).deleteById(customer.getId());
    }

}
