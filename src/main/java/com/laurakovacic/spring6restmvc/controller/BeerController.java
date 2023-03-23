package com.laurakovacic.spring6restmvc.controller;

import com.laurakovacic.spring6restmvc.model.Beer;
import com.laurakovacic.spring6restmvc.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController     // returns response body in JSON
public class BeerController {
    private final BeerService beerService;

    @RequestMapping("/api/v1/beer")     // mapping the path to the list of beers
    public List<Beer> listBeers() {        // view handler is Jackson which will produce JSON response
        return beerService.listBeers();
    }

    public Beer getBeerById(UUID id) {

        log.debug("Get Beer by Id - in controller");

        return beerService.getBeerById(id);

    }
}
