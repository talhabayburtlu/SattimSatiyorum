package com.springboot.SattimSatiyorum.rest.product;

import com.springboot.SattimSatiyorum.entity.product.Shopping;
import com.springboot.SattimSatiyorum.service.product.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class ShoppingRestController {

    private final ShoppingService shoppingService;

    @Autowired
    public ShoppingRestController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @GetMapping("/shoppings/{shoppingId}")
    public Shopping findById(@PathVariable int shoppingId) {
        Shopping shopping = (Shopping) shoppingService.findById(shoppingId);

        return shopping;
    }

    @PostMapping("/shoppings")
    public Shopping addShopping(@RequestBody Shopping shopping) {
        shopping.setId(0);
        shoppingService.save(shopping);

        return shopping;
    }

    @DeleteMapping("/shoppings/{shoppingId}")
    public Shopping deleteShopping(@PathVariable int shoppingId) {
        Shopping shopping = (Shopping) shoppingService.findById(shoppingId);

        shoppingService.deleteById(shoppingId);

        return shopping;
    }

}
