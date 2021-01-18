package com.springboot.SattimSatiyorum.controller.product;

import com.springboot.SattimSatiyorum.dto.product.ShoppingDTO;
import com.springboot.SattimSatiyorum.entity.feature.FeatureOption;
import com.springboot.SattimSatiyorum.entity.product.Product;
import com.springboot.SattimSatiyorum.entity.product.Shopping;
import com.springboot.SattimSatiyorum.service.feature.FeatureOptionService;
import com.springboot.SattimSatiyorum.service.product.ShoppingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ShoppingRestController {

    private final ShoppingService shoppingService;
    private final FeatureOptionService featureOptionService;
    private final ModelMapper modelMapper;

    @Autowired
    public ShoppingRestController(ShoppingService shoppingService, FeatureOptionService featureOptionService) {
        this.shoppingService = shoppingService;
        this.featureOptionService = featureOptionService;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping("/shoppings/{shoppingId}")
    public ShoppingDTO findById(@PathVariable int shoppingId) {
        Shopping shopping = (Shopping) shoppingService.findById(shoppingId);
        return toDTO(shopping);
    }

    @PostMapping("/shoppings")
    public ShoppingDTO addShopping(@RequestBody ShoppingDTO shoppingDTO) {
        Shopping shopping = toEntity(shoppingDTO);
        shopping.setId(0);
        shoppingService.save(shopping);
        return toDTO(shopping);
    }

    @DeleteMapping("/shoppings/{shoppingId}")
    public ShoppingDTO deleteShopping(@PathVariable int shoppingId) {
        Shopping shopping = (Shopping) shoppingService.findById(shoppingId);
        shoppingService.deleteById(shoppingId);
        return toDTO(shopping);
    }

    @GetMapping("/shoppings")
    public ArrayList<ShoppingDTO> getResidenceByHeader(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "") String subHeader) {
        ArrayList<Product> products = shoppingService.findProductByHeader(page, "Shopping", subHeader);
        return toDTOList(products.stream().map(p -> (Shopping) p).collect(Collectors.toCollection(ArrayList::new)));
    }

    private ShoppingDTO toDTO(Shopping shopping) {
        ArrayList<Integer> featureOptions = shopping.getFeatureOptions()
                .stream()
                .map(FeatureOption::getId)
                .collect(Collectors.toCollection(ArrayList::new));
        ShoppingDTO shoppingDTO = modelMapper.map(shopping, ShoppingDTO.class);
        shoppingDTO.setFeatureOptionIds(featureOptions);
        return shoppingDTO;
    }

    private Shopping toEntity(ShoppingDTO shoppingDTO) {
        ArrayList<FeatureOption> featureOptions = shoppingDTO.getFeatureOptionIds()
                .stream()
                .map(featureOptionService::findById)
                .collect(Collectors.toCollection(ArrayList::new));
        Shopping shopping = modelMapper.map(shoppingDTO, Shopping.class);
        shopping.setFeatureOptions(featureOptions);
        return shopping;
    }

    private ArrayList<ShoppingDTO> toDTOList(ArrayList<Shopping> shoppings) {
        return shoppings.stream().map(this::toDTO).collect(Collectors.toCollection(ArrayList::new));
    }
}
