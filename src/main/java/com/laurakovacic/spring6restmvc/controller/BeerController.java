package com.laurakovacic.spring6restmvc.controller;

import com.laurakovacic.spring6restmvc.model.Beer;
import com.laurakovacic.spring6restmvc.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController     // returns response body in JSON
@RequestMapping("/api/v1/beer")
public class BeerController {
    private final BeerService beerService;

    @PostMapping
//    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity handlePost(@RequestBody Beer beer) {  // @RequestBody tells Spring to bind JSON body to Beer object

        Beer savedBeer = beerService.saveNewBeer(beer);   // mimicking persistence operation

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)     // limiting to work with GET method only
    public List<Beer> listBeers() {        // view handler is Jackson which will produce JSON response
        return beerService.listBeers();
    }

    @RequestMapping(value = "/{beerId}", method = RequestMethod.GET)
    public Beer getBeerById(@PathVariable("beerId") UUID beerId) {

        log.debug("Get Beer by Id - in controller");

        return beerService.getBeerById(beerId);

    }
}
