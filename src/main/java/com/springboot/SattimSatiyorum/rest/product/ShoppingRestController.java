package com.springboot.SattimSatiyorum.rest.product;

import com.springboot.SattimSatiyorum.dto.product.ShoppingDTO;
import com.springboot.SattimSatiyorum.entity.Category;
import com.springboot.SattimSatiyorum.entity.product.Shopping;
import com.springboot.SattimSatiyorum.service.CategoryService;
import com.springboot.SattimSatiyorum.service.product.ShoppingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class ShoppingRestController {

    private final ShoppingService shoppingService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public ShoppingRestController(ShoppingService shoppingService, CategoryService categoryService) {
        this.shoppingService = shoppingService;
        this.categoryService = categoryService;
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

    private ShoppingDTO toDTO(Shopping shopping) {
        int category_id = shopping.getCategory().getId();

        ShoppingDTO shoppingDTO = modelMapper.map(shopping, ShoppingDTO.class);
        shoppingDTO.setCategory_id(category_id);
        return shoppingDTO;
    }

    private Shopping toEntity(ShoppingDTO shoppingDTO) {
        Category category = categoryService.findById(shoppingDTO.getCategory_id());

        Shopping shopping = modelMapper.map(shoppingDTO, Shopping.class);
        shopping.setCategory(category);
        return shopping;
    }
}
