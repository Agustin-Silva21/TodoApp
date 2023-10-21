package com.example.todoapp;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {

    private static List<Todo> todos ;
    private static int todoCount = 0;
    static {
        todos = new ArrayList<>();
        todos.add(new Todo(++todoCount, "agus", "Learn Spring MVC", LocalDate.now().plusDays(2), false));
        todos.add(new Todo(++todoCount, "Silvi", "Learn Anatomy", LocalDate.now().plusDays(6), false));
        todos.add(new Todo(++todoCount, "agus", "Learn Java", LocalDate.now().plusDays(10), false));
        todos.add(new Todo(++todoCount, "agus", "Learn Python", LocalDate.now().plusDays(5), false));
    }
    public List<Todo> findByUser(String username) {
        Predicate<? super Todo> todoPredicate = todo -> todo.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(todoPredicate).toList();
    }

    public void addNewTodo(String username, String desc,LocalDate targetDate,boolean isDone) {
        todos.add(new Todo(
                ++todoCount,
                username,
                desc,
                targetDate,
                isDone));
    }

    public void deleteTodoById(int id) {
        todos.removeIf(todo -> todo.getId() == id);
    }

    public Todo findById(int id) {
        return todos.stream().filter(todo -> todo.getId() == id).findFirst().get();
    }

    public void updateTodo(@Valid Todo todo) {
        deleteTodoById(todo.getId());
        todos.add(todo);
    }
}
