package com.internship.consumer.config;

import com.internship.consumer.batch.JobCompletionNotificationListener;
import com.internship.consumer.batch.ProductItemProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import store.bean.Product;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfiguration {

    public final JobBuilderFactory jobBuilderFactory;

    public final StepBuilderFactory stepBuilderFactory;

    @Value("${product-data}")
    public String resource;


    @Bean
    public FlatFileItemReader<Product> reader() {
        return new FlatFileItemReaderBuilder<Product>().name("productItemReader")
                .resource(new ClassPathResource(resource)).delimited()
                .names("id", "image", "name", "description")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {
                    {
                        setTargetType(Product.class);
                    }
                }).build();
    }

    @Bean
    public MongoItemWriter<Product> writer(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<Product>().template(mongoTemplate).collection("Product")
                .build();
    }


    @Bean
    public ProductItemProcessor processor() {
        return new ProductItemProcessor();
    }


    @Bean
    public Step step1(FlatFileItemReader<Product> itemReader, MongoItemWriter<Product> itemWriter) {
        return this.stepBuilderFactory.get("step1").<Product, Product>chunk(5).reader(itemReader)
                .processor(processor()).writer(itemWriter).build();
    }

    @Bean
    public Job updateProductJob(JobCompletionNotificationListener listener, Step step1) {
        return this.jobBuilderFactory.get("updateProductJob").incrementer(new RunIdIncrementer())
                .listener(listener).start(step1).build();
    }

}
