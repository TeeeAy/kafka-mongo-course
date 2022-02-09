package com.internship.producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import store.bean.Product;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class ProducerConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String brokers;

    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    @Value("${spring.kafka.partitions}")
    private int partitions;

    @Bean
    public ProducerFactory<String, Product> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
       configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                brokers);
        return new DefaultKafkaProducerFactory<>(configProps, new StringSerializer(), new JsonSerializer<>());
    }

    @Bean
    public NewTopic createTopic() {
        return new NewTopic(topic, partitions, (short) 1);
    }

    @Bean
    public KafkaTemplate<String, Product> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
