package com.laurakovacic.spring6restmvc.repositores;

import com.laurakovacic.spring6restmvc.bootstrap.BootstrapData;
import com.laurakovacic.spring6restmvc.entities.Beer;
import com.laurakovacic.spring6restmvc.model.BeerStyle;
import com.laurakovacic.spring6restmvc.services.BeerServiceImpl;
import com.laurakovacic.spring6restmvc.services.csv.BeerCsvServiceImpl;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@Import({BootstrapData.class, BeerCsvServiceImpl.class})   // BeerServiceImpl bc that is the service annotated class
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void getBeerListByName() {
        List<Beer> list = beerRepository.findAllByBeerNameIsLikeIgnoreCase("%IPA%");
        assertThat(list.size()).isEqualTo(336);
    }

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