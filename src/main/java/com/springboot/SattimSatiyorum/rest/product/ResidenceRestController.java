package com.springboot.SattimSatiyorum.rest.product;

import com.springboot.SattimSatiyorum.entity.product.Residence;
import com.springboot.SattimSatiyorum.service.product.ResidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ResidenceRestController {

    private final ResidenceService residenceService;

    @Autowired
    public ResidenceRestController(ResidenceService residenceService) {
        this.residenceService = residenceService;
    }

    @GetMapping("/residences/{residenceId}")
    public Residence findById(@PathVariable int residenceId) {
        Residence residence = (Residence) residenceService.findById(residenceId);

        return residence;
    }

    @PostMapping("/residences")
    public Residence addResidence(@RequestBody Residence residence) {
        residence.setId(0);
        residenceService.save(residence);

        return residence;
    }

    @DeleteMapping("/residences/{residenceId}")
    public Residence deleteResidence(@PathVariable int residenceId) {
        Residence residence = (Residence) residenceService.findById(residenceId);

        residenceService.deleteById(residenceId);

        return residence;
    }


}
