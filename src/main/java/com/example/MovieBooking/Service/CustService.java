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
public class CustService {
    @Autowired
    public CustomerRepo custRepo;
    public List<Customer> get_customer() {
        return custRepo.findAll();
    }

    public void add_customer(Customer cust) {
        Optional<Customer> c=custRepo.findByNameAndEmailid(cust.getName(),cust.getEmailid());
        if(c.isPresent())
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Data already exists");
        }
        custRepo.save(cust);
    }


    public void updating_customer(int id, Customer cust) {
        Optional<Customer> c= Optional.ofNullable(custRepo.findById(id).orElseThrow(() -> new CustomerNotFoundException(Integer.toString(id))));
        if(c.isEmpty())
        {
            throw new CustomerNotFoundException(" Customer Not found with id "+id);
        }
        Customer custo=c.get();
        custo.setName(cust.getName());
        custo.setEmailid(cust.getEmailid());
        custRepo.save(custo);
    }


    public void deleleting_customer(int id) {
        Optional<Customer> c=custRepo.findById(id);
        if(c.isEmpty())
        {
            throw new CustomerNotFoundException(" Customer Not found with id "+id);
        }
        custRepo.deleteById(id);
    }
}
