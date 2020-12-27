package com.springboot.SattimSatiyorum.controller;

import com.springboot.SattimSatiyorum.dto.CommercialDTO;
import com.springboot.SattimSatiyorum.entity.Commercial;
import com.springboot.SattimSatiyorum.entity.User;
import com.springboot.SattimSatiyorum.entity.product.Product;
import com.springboot.SattimSatiyorum.service.CommercialService;
import com.springboot.SattimSatiyorum.service.UserService;
import com.springboot.SattimSatiyorum.service.product.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CommercialRestController {

    private final CommercialService commercialService;
    private final UserService userService;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Autowired
    public CommercialRestController(CommercialService commercialService, UserService userService, @Qualifier("productServiceImpl") ProductService productService) {
        this.commercialService = commercialService;
        this.userService = userService;
        this.productService = productService;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping("/commercials/{commercialId}")
    public CommercialDTO getCommercial(@PathVariable int commercialId) {
        Commercial commercial = commercialService.findById(commercialId); // throws error if does not exist

        return toDTO(commercial);
    }

    @PostMapping("/commercials")
    public CommercialDTO addCommercial(@RequestBody CommercialDTO commercialDTO) {
        Commercial commercial = this.toEntity(commercialDTO);
        commercial.setId(0);
        commercialService.save(commercial);

        return toDTO(commercial);
    }

    @DeleteMapping("/commercials/{commercialId}")
    public CommercialDTO deleteCommercial(@PathVariable int commercialId) {
        Commercial commercial = commercialService.findById(commercialId); // throws error if does not exist
        commercialService.deleteById(commercialId);

        return toDTO(commercial);
    }

    @GetMapping("/commercials/active/{page}")
    public ArrayList<CommercialDTO> getAllActiveCommercials(@PathVariable int page) {
        ArrayList<Commercial> commercials = commercialService.findAllActiveCommercials(page);
        return toDTOList(commercials);
    }

    private Commercial toEntity(CommercialDTO commercialDTO) {
        User seller = userService.findById(commercialDTO.getSellerId());
        Product product = productService.findById(commercialDTO.getProductId());

        Commercial commercial = modelMapper.map(commercialDTO, Commercial.class);
        commercial.setSeller(seller);
        commercial.setProduct(product);
        return commercial;
    }

    private CommercialDTO toDTO(Commercial commercial) {
        int sellerId = commercial.getSeller().getId();
        int productId = commercial.getProduct().getId();

        CommercialDTO commercialDTO = modelMapper.map(commercial, CommercialDTO.class);
        commercialDTO.setSellerId(sellerId);
        commercialDTO.setProductId(productId);
        return commercialDTO;
    }

    private ArrayList<CommercialDTO> toDTOList(ArrayList<Commercial> commercials) {
        return commercials.stream().map(this::toDTO).collect(Collectors.toCollection(ArrayList::new));
    }
}