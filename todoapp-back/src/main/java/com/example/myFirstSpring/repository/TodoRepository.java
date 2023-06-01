package com.example.myFirstSpring.repository;

import com.example.myFirstSpring.model.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface TodoRepository extends MongoRepository<Todo, String> {
    Optional<Todo> findTodoById(String id);
    void deleteTodoById(String id);


}
