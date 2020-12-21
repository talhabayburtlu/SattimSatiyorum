package com.springboot.SattimSatiyorum.rest;

import com.springboot.SattimSatiyorum.dto.CategoryDTO;
import com.springboot.SattimSatiyorum.entity.Category;
import com.springboot.SattimSatiyorum.entity.feature.Feature;
import com.springboot.SattimSatiyorum.entity.product.Product;
import com.springboot.SattimSatiyorum.service.CategoryService;
import com.springboot.SattimSatiyorum.service.feature.FeatureService;
import com.springboot.SattimSatiyorum.service.product.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CategoryRestController {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final FeatureService featureService;
    private final ModelMapper modelMapper;

    public CategoryRestController(CategoryService categoryService, @Qualifier("productServiceImpl") ProductService productService, FeatureService featureService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.featureService = featureService;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping("/categories/{categoryId}")
    public CategoryDTO getCategory(@PathVariable int categoryId) {
        Category category = categoryService.findById(categoryId);
        return toDTO(category);
    }

    @PostMapping("/categories")
    public CategoryDTO addCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = toEntity(categoryDTO);
        category.setId(0);
        categoryService.save(category);
        return toDTO(category);
    }

    @DeleteMapping("/categories/{categoryId}")
    public CategoryDTO deleteCategory(@PathVariable int categoryId) {
        Category category = categoryService.findById(categoryId);
        categoryService.deleteById(categoryId);

        return toDTO(category);
    }

    private CategoryDTO toDTO(Category category) {
        ArrayList<Integer> featuresId = category.getFeatures().stream()
                .map(Feature::getId).collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Integer> productIds = category.getProducts().stream()
                .map(Product::getId).collect(Collectors.toCollection(ArrayList::new));

        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        categoryDTO.setFeatureIds(featuresId);
        categoryDTO.setProductIds(productIds);
        return categoryDTO;
    }

    private Category toEntity(CategoryDTO categoryDTO) {
        ArrayList<Feature> features = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();

        Category category = modelMapper.map(categoryDTO, Category.class);
        category.setFeatures(features);
        category.setProducts(products);
        return category;
    }
}
