package com.internship.consumer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import store.bean.Product;

public interface ProductRepository extends MongoRepository<Product, String> {


}
