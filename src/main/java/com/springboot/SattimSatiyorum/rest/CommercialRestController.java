package com.springboot.SattimSatiyorum.rest;

import com.springboot.SattimSatiyorum.dto.converter.CommercialConverter;
import com.springboot.SattimSatiyorum.entity.Commercial;
import com.springboot.SattimSatiyorum.service.CommercialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommercialRestController {

    private final CommercialService commercialService;
    private final CommercialConverter commercialConverter;

    @Autowired
    public CommercialRestController(CommercialService commercialService, CommercialConverter commercialConverter) {
        this.commercialService = commercialService;
        this.commercialConverter = commercialConverter;
    }

    @GetMapping("/commercials/{commercialId}")
    public Commercial getCommercial(@PathVariable int commercialId) {
        Commercial commercial = commercialService.findById(commercialId); // throws error if does not exist

        return commercial;
    }

    @PostMapping("/commercials")
    public Commercial addCommercial(@RequestBody Commercial commercial) {
        commercial.setId(0);
        commercialService.save(commercial);

        return commercial;
    }

    @DeleteMapping("/commercials/{commercialId}")
    public Commercial deleteCommercial(@PathVariable int commercialId) {
        Commercial commercial = commercialService.findById(commercialId); // throws error if does not exist

        commercialService.deleteById(commercialId);

        return commercial;
    }


}
