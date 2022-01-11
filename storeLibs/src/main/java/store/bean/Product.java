package store.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.lang.annotation.Documented;

@Data
@Document(collection="Employee")
public class Product {

    @Field(value = "Name")
    private String name;

    @Field(value = "Description")
    private String description;

    @Id
    private String id;

}
