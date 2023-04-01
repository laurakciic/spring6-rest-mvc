package com.laurakovacic.spring6restmvc.repositores;

import com.laurakovacic.spring6restmvc.entities.Beer;
import com.laurakovacic.spring6restmvc.entities.BeerOrder;
import com.laurakovacic.spring6restmvc.entities.BeerOrderShipment;
import com.laurakovacic.spring6restmvc.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
class BeerOrderRepositoryTest {
    @Autowired
    BeerOrderRepository beerOrderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BeerRepository beerRepository;

    Customer testCustomer;
    Beer testBeer;

    @BeforeEach
    void setUp() {
        testCustomer = customerRepository.findAll().get(0);
        testBeer = beerRepository.findAll().get(0);
    }

    @Transactional  // bc of lazy load from the customer
    @Test
    void beerOrders() {
        BeerOrder beerOrder = BeerOrder.builder()
                .customerRef("Test order")
                .customer(testCustomer)
                .beerOrderShipment(BeerOrderShipment.builder()
                        .trackingNumber("1235r")
                        .build())
                .build();

        BeerOrder savedBeerOrder = beerOrderRepository.save(beerOrder);


        System.out.println(savedBeerOrder.getCustomerRef());
    }
}