package com.laurakovacic.spring6restmvc.controller;

import com.laurakovacic.spring6restmvc.model.Customer;
import com.laurakovacic.spring6restmvc.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @PutMapping("{customerId}")     // limits the method to responding to HTTP PUT requests only
    public ResponseEntity updateById(@PathVariable("customerId") UUID customerId, @RequestBody Customer customer){

        customerService.updateCustomerById(customerId, customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{customerId}")
    public ResponseEntity deleteById(@PathVariable("customerId") UUID customerId) {

        customerService.deleteCustomerById(customerId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity handlePost(@RequestBody Customer customer) {      // @RequestBody - parsing JSON body to Customer object

        Customer savedCustomer = customerService.saveNewCustomer(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + savedCustomer.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PatchMapping("{customerId}")
    public ResponseEntity patchCustomerById(@PathVariable("customerId") UUID customerId, @RequestBody Customer customer){

        customerService.patchCustomerById(customerId, customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> listAllCustomers() {
        return customerService.listCustomers();
    }

    @RequestMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") UUID id) {
        return customerService.getCustomerById(id);
    }
}
