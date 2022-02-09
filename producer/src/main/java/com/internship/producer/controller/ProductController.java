package com.internship.producer.controller;

import com.internship.producer.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import store.bean.Product;

import static com.internship.producer.controller.ProductControllerConstants.ADD_PRODUCT_URL;
import static com.internship.producer.controller.ProductControllerConstants.MAPPING_URL;

@RestController
@RequestMapping(MAPPING_URL)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(ADD_PRODUCT_URL)
    public void addProduct(@RequestBody Product product) {
        productService.sendMessage(product);
    }


}
