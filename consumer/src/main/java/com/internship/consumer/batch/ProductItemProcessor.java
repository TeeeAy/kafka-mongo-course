package com.internship.consumer.batch;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import store.bean.Product;

@Slf4j
public class ProductItemProcessor implements ItemProcessor<Product, Product> {

    @Override
    public Product process(@NonNull Product item) {
        log.info("processing product data.....{}", item);
        return item;
    }

}