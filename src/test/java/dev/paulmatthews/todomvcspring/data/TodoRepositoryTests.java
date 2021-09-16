package dev.paulmatthews.todomvcspring.data;

import dev.paulmatthews.todomvcspring.models.Todo.NewTodo;
import dev.paulmatthews.todomvcspring.models.Todo.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

public class TodoRepositoryTests {

    @BeforeEach
    public void resetRepository() {
        TodoRepository.reset();
    }

    @Test
    public void indirectTestGetNextId() {
        NewTodo newTodo = new NewTodo("example todo item");
        Todo todo = TodoRepository.addNewTodo(newTodo);
        Assertions.assertEquals(1L, todo.getId());
    }

    @Test
    public void testAddNewTodo() {
        NewTodo newTodo = new NewTodo("example todo item");
        Todo todo = TodoRepository.addNewTodo(newTodo);
        Assertions.assertEquals(1L, todo.getId());
        Assertions.assertEquals("example todo item", todo.getName());
        Assertions.assertNotEquals(LocalDateTime.now(), todo.getDateTime());
        Assertions.assertFalse(todo.isCompleted());
    }

    @Test
    public void testGetAll() {
        Assertions.assertEquals(0, TodoRepository.getAll().size());
        NewTodo newTodo = new NewTodo("example todo item");
        Todo todo = TodoRepository.addNewTodo(newTodo);
        Assertions.assertEquals(1, TodoRepository.getAll().size());
        Assertions.assertEquals(todo, TodoRepository.getAll().get(0));
    }

    @Test
    public void testReset() {
        NewTodo newTodo = new NewTodo("example todo item");
        TodoRepository.addNewTodo(newTodo);
        TodoRepository.reset();
        Assertions.assertEquals(0, TodoRepository.getAll().size());
    }

    @Test
    public void testFindByIdExists() {
        Todo todo = TodoRepository.addNewTodo(new NewTodo("example todo item"));
        Optional<Todo> maybeTodo = TodoRepository.findById(todo.getId());
        Assertions.assertTrue(maybeTodo.isPresent());
    }

    @Test
    public void testFindByIdNoExists() {
        Optional<Todo> maybeTodo = TodoRepository.findById(0L);
        Assertions.assertTrue(maybeTodo.isEmpty());
    }

    @Test
    public void testRemoveByIdExists() {
        Todo testTodo1 = TodoRepository.addNewTodo(new NewTodo("example item 1"));
        Todo testTodo2 = TodoRepository.addNewTodo(new NewTodo("example item 2"));
        Todo testTodo3 = TodoRepository.addNewTodo(new NewTodo("example item 3"));
        Assertions.assertEquals(3, TodoRepository.getAll().size());
        TodoRepository.removeById(testTodo2.getId());
        Assertions.assertEquals(2, TodoRepository.getAll().size());
        Assertions.assertEquals(testTodo1, TodoRepository.findById(testTodo1.getId()).get());
        Assertions.assertEquals(testTodo3, TodoRepository.findById(testTodo3.getId()).get());
        Assertions.assertTrue(TodoRepository.findById(testTodo2.getId()).isEmpty());
    }

    @Test
    public void testRemoveByIdNoExists() {
        Todo testTodo1 = TodoRepository.addNewTodo(new NewTodo("example item 1"));
        Todo testTodo2 = TodoRepository.addNewTodo(new NewTodo("example item 2"));
        Todo testTodo3 = TodoRepository.addNewTodo(new NewTodo("example item 3"));
        Assertions.assertEquals(3, TodoRepository.getAll().size());
        TodoRepository.removeById(-1L);
        Assertions.assertEquals(3, TodoRepository.getAll().size());
    }

}
