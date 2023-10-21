package com.example.todoapp;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@SessionAttributes("username")
@Controller
public class TodoControllerJPA {

    private TodoRepository todoRepository;

    public TodoControllerJPA(TodoRepository todoRepository) {
        super();
        this.todoRepository = todoRepository;
    }

    @RequestMapping("/list-todos")
    public String getTodos(ModelMap model) {
        String username = getLoggedInUserName(model);

        List<Todo> todos = todoRepository.findByUsername(username);
        model.put("todos", todos);

        return "listOfTodos";
    }

    @GetMapping("/add-Todo")
    public String showNewTodoPage(ModelMap model) {
        String username = getLoggedInUserName(model);
        Todo todo = new Todo(0, username, "", LocalDate.now(),false);
        model.put("todo", todo);
        return "addTodo";
    }

    @PostMapping("/add-Todo")
    public String addNewTodoPage(ModelMap model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "addTodo";
        }
        String username = getLoggedInUserName(model);
        todo.setUsername(username);
        todoRepository.save(todo);
        return "redirect:/list-todos";
    }

    @RequestMapping("/delete-Todo")
    public String deleteTodoPage(@RequestParam int id) {
        todoRepository.deleteById(id);
        return "redirect:/list-todos";
    }

    @GetMapping("/update-Todo")
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        Todo todo = todoRepository.findById(id).get();
        model.put("todo", todo);
        return "addTodo";
    }

    @PostMapping("/update-Todo")
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "addTodo";
        }
        String username = getLoggedInUserName(model);
        todo.setUsername(username);
        todoRepository.save(todo);
        return "redirect:/list-todos";
    }

    private String getLoggedInUserName(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

}
