package com.springboot.SattimSatiyorum.rest;

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

    private Commercial toEntity(CommercialDTO commercialDTO) {
        User seller = userService.findById(commercialDTO.getSeller_id());
        Product product = productService.findById(commercialDTO.getProduct_id());

        Commercial commercial = modelMapper.map(commercialDTO, Commercial.class);
        commercial.setSeller(seller);
        commercial.setProduct(product);
        return commercial;
    }

    private CommercialDTO toDTO(Commercial commercial) {
        int sellerId = commercial.getSeller().getId();
        int productId = commercial.getProduct().getId();

        CommercialDTO commercialDTO = modelMapper.map(commercial, CommercialDTO.class);
        commercialDTO.setSeller_id(sellerId);
        commercialDTO.setProduct_id(productId);
        return commercialDTO;
    }

}