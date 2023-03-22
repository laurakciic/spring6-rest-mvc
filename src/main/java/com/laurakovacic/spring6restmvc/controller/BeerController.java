package com.laurakovacic.spring6restmvc.controller;

import com.laurakovacic.spring6restmvc.services.BeerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class BeerController {
    private final BeerService beerService;

}
