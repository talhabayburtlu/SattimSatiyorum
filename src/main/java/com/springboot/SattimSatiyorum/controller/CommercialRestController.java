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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
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
        org.springframework.security.core.userdetails.User securityUser =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User seller = userService.findUserWithUniques(securityUser.getUsername(), null);
        commercialDTO.setSellerId(seller.getId());

        Commercial commercial = this.toEntity(commercialDTO);
        commercial.setId(0);
        commercialService.save(commercial);

        return toDTO(commercial);
    }

    @DeleteMapping("/commercials/{commercialId}")
    public CommercialDTO deleteCommercial(@PathVariable int commercialId) throws Exception {
        Commercial commercial = commercialService.findById(commercialId); // throws error if does not exist

        org.springframework.security.core.userdetails.User securityUser =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!securityUser.getUsername().equals(commercial.getSeller().getMail()))
            throw new Exception("You are not authorized to delete this commercial.");

        commercialService.deleteById(commercialId);

        return toDTO(commercial);
    }

    @GetMapping("/commercials/sold/{userId}")
    public ArrayList<CommercialDTO> getSoldCommercialsByActiveFromSeller
            (@RequestParam(defaultValue = "1") int page, @RequestParam(value = "active") Boolean active, @PathVariable int userId) {
        ArrayList<Commercial> commercials;
        if (active)
            commercials = commercialService.findSoldCommercialsByActiveFromSeller(page, userId);
        else
            commercials = commercialService.findSoldCommercialsByNotActiveFromSeller(page, userId);
        return toDTOList(commercials);
    }

    @GetMapping("/commercials/bought/{userId}")
    public ArrayList<CommercialDTO> getSoldCommercialsByActiveFromSeller
            (@RequestParam(defaultValue = "1") int page, @PathVariable int userId) {
        ArrayList<Commercial> commercials = commercialService.findBoughtCommercialsFromBuyer(page, userId);
        return toDTOList(commercials);
    }


    @GetMapping("/commercials")
    public ArrayList<CommercialDTO> getActiveCommercials(@RequestParam(defaultValue = "1") int page) {
        ArrayList<Commercial> commercials = commercialService.findActiveCommercials(page);
        return toDTOList(commercials);
    }

    @GetMapping("/commercials/foi/")
    public ArrayList<CommercialDTO> getActiveCommercialsByFeatureOptionsIds
            (@RequestParam(defaultValue = "1") int page, @RequestParam(value = "to") ArrayList<Integer> featureOptionIds) {
        ArrayList<Commercial> commercials = commercialService.findActiveCommercialsByFeatureOptionIds(page, featureOptionIds);
        return toDTOList(commercials);
    }

    @GetMapping("/commercials/date")
    public ArrayList<CommercialDTO> getActiveCommercialsByDate
            (@RequestParam(defaultValue = "1") int page, @RequestParam(value = "date") Date date) {
        ArrayList<Commercial> commercials = commercialService.findActiveCommercialsByDate(page, date);
        return toDTOList(commercials);
    }

    @GetMapping("/commercials/price")
    public ArrayList<CommercialDTO> getActiveCommercialsByPrice
            (@RequestParam(defaultValue = "1") int page, @RequestParam(value = "min") int min, @RequestParam(value = "max") int max) {
        ArrayList<Commercial> commercials = commercialService.findActiveCommercialsByPrice(page, min, max);
        return toDTOList(commercials);
    }

    @GetMapping("/commercials/urgent")
    public ArrayList<CommercialDTO> getActiveCommercialsByIsUrgent
            (@RequestParam(defaultValue = "1") int page) {
        ArrayList<Commercial> commercials = commercialService.findActiveCommercialsByIsUrgent(page);
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