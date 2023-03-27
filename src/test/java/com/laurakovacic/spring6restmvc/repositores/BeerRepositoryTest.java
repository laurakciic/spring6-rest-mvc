package com.laurakovacic.spring6restmvc.repositores;

import com.laurakovacic.spring6restmvc.entities.Beer;
import com.laurakovacic.spring6restmvc.model.BeerStyle;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void saveBeerNameTooLong() {

        assertThrows(ConstraintViolationException.class, () -> {
            beerRepository.save(Beer.builder()
                    .beerName("01234567890123456789012345678901234567890123456789012345678901234567890123456789")
                    .beerStyle(BeerStyle.PALE_ALE)
                    .upc("1239")
                    .price(new BigDecimal("11.99"))
                    .build());

            beerRepository.flush();
        });
    }

    @Test
    void saveBeer() {
        Beer savedBeer = beerRepository.save(Beer.builder()
                .beerName("Vukovarsko")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("1239")
                .price(new BigDecimal("11.99"))
                .build());

        /**
         *  flush method is necessary because test splice is used (@DataJpaTest)
         *  and flush ensures that hibernate will immediately write to the DB
         *
         *  (problem was that save() happened too quickly
         *  and only object was returned, error was not flushed out yet - kind of like a lazy write)
         */
        beerRepository.flush();

        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();
    }
}