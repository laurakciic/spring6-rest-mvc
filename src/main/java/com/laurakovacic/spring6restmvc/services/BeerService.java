package com.laurakovacic.spring6restmvc.services;

import com.laurakovacic.spring6restmvc.model.Beer;

import java.util.UUID;

public interface BeerService {
    Beer getBeerById(UUID id);
}
