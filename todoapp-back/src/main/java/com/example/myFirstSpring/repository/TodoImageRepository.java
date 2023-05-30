package com.example.myFirstSpring.repository;


import com.example.myFirstSpring.model.TodoImage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoImageRepository extends MongoRepository<TodoImage, String> {}