package com.example.MovieBooking.Service;

import com.example.MovieBooking.Error.CustomerNotFoundException;
import com.example.MovieBooking.Repository.CustomerRepo;
import com.example.MovieBooking.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    public CustomerRepo customerrepo;
    public List<Customer> getCustomer() {
        return customerrepo.findAll();
    }

    public void addCustomer(Customer customer) {
        Optional<Customer> customer1=customerrepo.findByNameAndEmailid(customer.getName(),customer.getEmailid());
        if(customer1.isPresent())
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Data already exists");
        }
        customerrepo.save(customer);
    }


    public void updateCustomer(int id, Customer customer) {
        Optional<Customer> customer1= Optional.ofNullable(customerrepo.findById(id).orElseThrow(() -> new CustomerNotFoundException(Integer.toString(id))));
        if(customer1.isEmpty())
        {
            throw new CustomerNotFoundException(" Customer Not found with id "+id);
        }
        Customer newcustomer=customer1.get();
        newcustomer.setName(customer.getName());
        newcustomer.setEmailid(customer.getEmailid());
        customerrepo.save(newcustomer);
    }


    public void deleteCustomer(int id) {
        Optional<Customer> customer=customerrepo.findById(id);
        if(customer.isEmpty())
        {
            throw new CustomerNotFoundException(" Customer Not found with id "+id);
        }
        customerrepo.deleteById(id);
    }
}
