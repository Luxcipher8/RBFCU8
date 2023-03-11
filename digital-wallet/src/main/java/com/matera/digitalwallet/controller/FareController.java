package com.matera.digitalwallet.controller;

import com.matera.digitalwallet.model.entities.FareType;
import com.matera.digitalwallet.repository.FareTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tarifas")
public class FareController {

    @Autowired
    private FareTypeRepository fareTypeRepository;

    @GetMapping
    public FareType findByName(@RequestParam String name) {
        return fareTypeRepository.findByNameContaining(name);
    }
}
