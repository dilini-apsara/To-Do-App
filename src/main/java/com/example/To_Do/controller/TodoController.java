package com.example.To_Do.controller;

import com.example.To_Do.model.Todo;
import com.example.To_Do.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {


    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return todoService.createTodo(todo);
    }

    @GetMapping
    public Page<Todo> getAllTodos(Pageable pageable) {
        return todoService.getAllTodos(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Integer id) {
        Todo todo = todoService.getTodoById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        return ResponseEntity.ok(todo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Integer id, @RequestBody Todo todoDetails) {
        Todo updatedTodo = todoService.updateTodo(id, todoDetails);
        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable Integer id) {
        todoService.deleteTodoById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public Page<Todo> searchTodos(@RequestParam String keyword, Pageable pageable) {
        return todoService.searchTodos(keyword, pageable);
    }

}
