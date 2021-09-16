package dev.paulmatthews.todomvcspring.controllers;

import dev.paulmatthews.todomvcspring.data.TodoRepository;
import dev.paulmatthews.todomvcspring.models.Todo.NewTodo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping
    public String getIndex(Model model) {
//        TodoRepository.addNewTodo(new NewTodo("example todo 1"));
        model.addAttribute("TodoList", TodoRepository.getAll());
        return "index";
    }
}
