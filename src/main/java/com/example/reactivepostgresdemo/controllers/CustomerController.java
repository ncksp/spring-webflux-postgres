package com.example.reactivepostgresdemo.controllers;

import com.example.reactivepostgresdemo.model.Customer;
import com.example.reactivepostgresdemo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.annotation.Nullable;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerRepository repository;

    @GetMapping()
    @Nullable
    public Flux<Customer> getCustomers(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Customer> getCustomer(@PathVariable Integer id){
        return repository.findById(id);
    }

    @PostMapping(
        consumes = {
            MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            MediaType.APPLICATION_JSON_VALUE
        }
    )
    public Mono<Customer> createCustomer(@RequestBody Customer customer){
        return repository.save(customer);
    }

    @PutMapping(value = "/{id}", consumes = {
            MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    })
    public Mono<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable Integer id){
        return repository.findById(id)
                .map(c -> {
                    c.setName(customer.getName());
                    return c;
                }).flatMap(c -> repository.save(c));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteCustomer(@PathVariable Integer id){
        return repository.deleteById(id);
    }
}
