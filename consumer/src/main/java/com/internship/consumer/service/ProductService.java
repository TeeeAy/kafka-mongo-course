package com.internship.consumer.service;

import com.internship.consumer.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import store.bean.Product;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @KafkaListener(topics = "${spring.kafka.template.default-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeMessage(Product product) {
        productRepository.save(product);
    }
}