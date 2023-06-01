package com.example.myFirstSpring.restcontroller;

import com.example.myFirstSpring.model.Todo;
import com.example.myFirstSpring.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/todo")
public class TodoRestController {
    //here we are creating our end-point rest API
    @Autowired
    TodoRepository todoRepository;

    /*@GetMapping("populate")
    public ResponseEntity<HashMap<String, Todo> > populate(@RequestParam("qty") int qty) {
        //
        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "populate");
        headers.add("version", "api 1.0");

        HashMap<String, Todo> fakeBooks = todoRepository.populateFakeBooks(qty);

        if (fakeBooks.size() > 0 ) {
            headers.add("statusOperation", "success");
            return ResponseEntity.accepted().headers(headers).body(fakeBooks);
        } else {
            headers.add("statusOperation", "not populated");
            return ResponseEntity.accepted().body(null);
        }
    }*/

    //CRUD: read
    @GetMapping
    public ResponseEntity<Iterable<Todo>> getAllTodos() {
        //
        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "findAll");
        headers.add("version", "api 1.0");
        headers.add("statusOperation", "success");

        return ResponseEntity.accepted().headers(headers).body(todoRepository.findAll());
    }



    //CRUD: create
    @PostMapping(path = "createTodo", consumes = "application/JSON")
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo) {
        //
        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "createTodo");
        headers.add("version", "api 1.0");
        headers.add("statusOperation", "success");

        Todo todoCreated = todoRepository.save(todo);
        System.out.println("todo:" + todo);
        if (todoCreated != null) {
            headers.add("statusOperation", "success");
            return ResponseEntity.accepted().headers(headers).body(todoCreated);
        } else {
            headers.add("statusOperation", "not created");
            return ResponseEntity.accepted().body(null);
        }
    }

    //CRUD: delete

    @DeleteMapping
    public ResponseEntity<Todo> deleteTodo(@RequestParam String id) {
        //
        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "deleteBook");
        headers.add("version", "api 1.0");

        Optional<Todo> todoFound = todoRepository.findTodoById(id);
        boolean isTodo = todoFound.isPresent();
        if (isTodo) {
            //Optional<Book> deletedBook = bookservice.deleteBookById(id);
            todoRepository.deleteTodoById(id);
            headers.add("operationStatus", "deleted");
            return ResponseEntity.accepted().headers(headers).body(todoFound.get());
        } else {
            headers.add("operationStatus", "not found");
            return ResponseEntity.accepted().body(null);
        }
    }
    @PutMapping("/updateTodo/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable String id, @RequestBody Todo dataTodo) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "completeTodo");
        headers.add("version", "api 1.0");

        Optional<Todo> todoFound = todoRepository.findTodoById(id);


        if (todoFound.isPresent()){
            //System.out.println(todoFound.get() + " -- " +  dataTodo);
            todoRepository.save(dataTodo);
            headers.add("operationStatus","updated");
            return  ResponseEntity.accepted().headers(headers).body(todoFound.get());
        } else {
            headers.add("operationStatus","not found");
            return ResponseEntity.accepted().headers(headers).body(null);}


    }

    /*
    //CRUD: update
    @PutMapping("/updateTodo/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable String id, @RequestBody Todo dataTodo) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "updateTodo");
        headers.add("version", "api 1.0");

        Optional<Todo> todoFound = todoRepository.findTodoById(id);


        if (todoFound.isPresent()){
            todoRepository.updateTodo(todoFound.get(), dataTodo);
            headers.add("operationStatus","updated");
            return  ResponseEntity.accepted().headers(headers).body(todoFound.get());
        } else {
            headers.add("operationStatus","not found");
            return ResponseEntity.accepted().headers(headers).body(null);}


    }*/

    //CRUD: delete book by title
    @DeleteMapping("deleteTodoByTitle")
    public ResponseEntity<Todo> deleteBookByTitle(@RequestParam String title) {
        //
        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "deleteBookByTitle");
        headers.add("version", "api 1.0");

        //findBookByTitle(String title)
        //deleteBookByTitle(S   tring title)

        return null;

    }

    //CRUD: read, find one book by id
    @GetMapping("getTodo")
    public ResponseEntity<Todo> findBookById(@RequestParam("id") String id) {
        //
        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "findTodoById");
        headers.add("version", "api 1.0");

        Optional<Todo> todoFound = todoRepository.findTodoById(id);
        if (todoFound.isPresent()) {
            headers.add("statusOperation", "success");
            return ResponseEntity.accepted().headers(headers).body(todoFound.get());
        } else {
            headers.add("statusOperation", "not found");
            return ResponseEntity.accepted().body(null);
        }
    }
}
