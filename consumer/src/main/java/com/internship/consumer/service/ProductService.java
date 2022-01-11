package com.internship.consumer.service;

import com.internship.consumer.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import store.bean.Product;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @KafkaListener(topics = "test_topic",groupId = "group_id")
    public void consumeMessage(Product product){
        productRepository.save(product);
        System.out.println(product);
    }
}