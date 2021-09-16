package dev.paulmatthews.todomvcspring.controllers;

import dev.paulmatthews.todomvcspring.data.TodoRepository;
import dev.paulmatthews.todomvcspring.models.Todo.NewTodo;
import dev.paulmatthews.todomvcspring.models.Todo.Todo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "/todo")
public class TodoController {

    @PostMapping
    public String addTodo(@ModelAttribute NewTodo newTodo, Model model) {
        TodoRepository.addNewTodo(newTodo);
        model.addAttribute("TodoList", TodoRepository.getAll());
        return "redirect:";
    }

    @GetMapping(value = "/{id}/complete")
    public String markItemAsComplete(@PathVariable Long todoId) {
        Optional<Todo> maybeTodo = TodoRepository.findById(todoId);
        if(maybeTodo.isEmpty()) {
            return "redirect:";
        }
        maybeTodo.get().setCompleted(true);
        return "redirect:";
    }
}
