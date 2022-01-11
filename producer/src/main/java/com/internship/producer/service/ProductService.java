package com.internship.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import store.bean.Product;

@Service
public class ProductService {

    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    private final KafkaTemplate<String, Product> kafkaTemplate;

    @Autowired
    public ProductService( KafkaTemplate<String, Product> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Product product){
        this.kafkaTemplate.send(topic, product);
    }

}
