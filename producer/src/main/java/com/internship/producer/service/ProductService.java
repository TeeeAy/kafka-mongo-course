package com.internship.producer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import store.bean.Product;

@Service
public class ProductService {

    private static final String TOPIC = "test_topic";

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public ProductService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Product product) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String message;
        message = objectMapper.writeValueAsString(product);
        this.kafkaTemplate.send(TOPIC, message);
    }

    @Bean
    public NewTopic createTopic() {
        return new NewTopic(TOPIC, 3, (short) 1);
    }

}
