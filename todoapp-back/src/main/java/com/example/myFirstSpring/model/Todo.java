    package com.example.myFirstSpring.model;

    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import java.time.LocalDate;
    import org.bson.types.Binary;
    import org.springframework.data.annotation.Id;
    import org.springframework.data.mongodb.core.mapping.Document;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Document(collection = "todo")
    public class Todo {

        @Id
        private String id;
        private String text;
        private String author;
        private LocalDate due;
        private boolean completed;
        //private Binary image;
    }
