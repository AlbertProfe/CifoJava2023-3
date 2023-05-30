package com.example.myFirstSpring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "TodoImage")
public class TodoImage {

    @Id
    private String id;
    private String todoId;
    private String name;
    private Binary image;
}
