package dev.paulmatthews.todomvcspring.data;

import dev.paulmatthews.todomvcspring.models.Todo.NewTodo;
import dev.paulmatthews.todomvcspring.models.Todo.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TodoRepository {
    private static Long nextId = 0L;
    private static Long getNextId() {
        nextId++;
        return nextId;
    }

    private static List<Todo> items = new ArrayList<>();

    public static void reset() {
        nextId = 0L;
        items.clear();
    }

    public static Todo addNewTodo(NewTodo newTodo) {
        Todo theTodo = new Todo(getNextId(), newTodo.getName());
        items.add(theTodo);
        return theTodo;
    }

    public static List<Todo> getAll() {
        return items;
    }

    public static Optional<Todo> findById(Long id) {
        for(Todo todo : getAll()) {
            if(todo.getId().equals(id)) {
                return Optional.of(todo);
            }
        }
        return Optional.empty();
    }

    public static void removeById(Long id) {
        int removeIndex = 0;
        for(Todo todo : getAll()) {
            if(todo.getId().equals(id)) {
                items.remove(removeIndex);
                break;
            }
            removeIndex++;
        }
    }
}
