package com.springboot.SattimSatiyorum.rest.product;

import com.springboot.SattimSatiyorum.dto.product.ResidenceDTO;
import com.springboot.SattimSatiyorum.entity.Category;
import com.springboot.SattimSatiyorum.entity.product.Residence;
import com.springboot.SattimSatiyorum.service.CategoryService;
import com.springboot.SattimSatiyorum.service.product.ResidenceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ResidenceRestController {

    private final ResidenceService residenceService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public ResidenceRestController(ResidenceService residenceService, CategoryService categoryService) {
        this.residenceService = residenceService;
        this.categoryService = categoryService;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping("/residences/{residenceId}")
    public ResidenceDTO findById(@PathVariable int residenceId) {
        Residence residence = (Residence) residenceService.findById(residenceId);
        return toDTO(residence);
    }

    @PostMapping("/residences")
    public ResidenceDTO addResidence(@RequestBody ResidenceDTO residenceDTO) {
        Residence residence = toEntity(residenceDTO);
        residence.setId(0);
        residenceService.save(residence);
        return toDTO(residence);
    }

    @DeleteMapping("/residences/{residenceId}")
    public ResidenceDTO deleteResidence(@PathVariable int residenceId) {
        Residence residence = (Residence) residenceService.findById(residenceId);
        residenceService.deleteById(residenceId);
        return toDTO(residence);
    }

    private ResidenceDTO toDTO(Residence residence) {
        int category_id = residence.getCategory().getId();

        ResidenceDTO residenceDTO = modelMapper.map(residence, ResidenceDTO.class);
        residenceDTO.setCategory_id(category_id);
        return residenceDTO;
    }

    private Residence toEntity(ResidenceDTO residenceDTO) {
        Category category = categoryService.findById(residenceDTO.getCategory_id());

        Residence residence = modelMapper.map(residenceDTO, Residence.class);
        residence.setCategory(category);
        return residence;
    }
}
