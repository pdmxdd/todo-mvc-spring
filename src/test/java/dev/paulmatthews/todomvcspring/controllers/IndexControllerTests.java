package dev.paulmatthews.todomvcspring.controllers;

import dev.paulmatthews.todomvcspring.IntegrationTestConfig;
import dev.paulmatthews.todomvcspring.data.TodoRepository;
import dev.paulmatthews.todomvcspring.models.Todo.NewTodo;
import dev.paulmatthews.todomvcspring.models.Todo.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTestConfig
public class IndexControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void beforeEach() {
        TodoRepository.reset();
    }

    @Test
    public void getIndex() throws Exception {
        Todo testTodoOne = TodoRepository.addNewTodo(new NewTodo("example todo 1"));
        Todo testTodoTwo = TodoRepository.addNewTodo(new NewTodo("example todo 2"));

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().string(containsString(testTodoOne.getName())))
            .andExpect(MockMvcResultMatchers.content().string(containsString(testTodoTwo.getName())))
            .andExpect(MockMvcResultMatchers.content().string(containsString("/todo/" + testTodoOne.getId() + "/complete")))
            .andExpect(MockMvcResultMatchers.content().string(containsString("/todo/" + testTodoOne.getId() + "/delete")))
            .andExpect(MockMvcResultMatchers.content().string(containsString("/todo/" + testTodoTwo.getId() + "/complete")))
            .andExpect(MockMvcResultMatchers.content().string(containsString("/todo/" + testTodoTwo.getId() + "/delete")));
    }
}
