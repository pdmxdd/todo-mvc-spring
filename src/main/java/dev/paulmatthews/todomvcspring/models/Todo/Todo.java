package dev.paulmatthews.todomvcspring.models.Todo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class Todo {
    private Long id;
    private String name;
    private boolean completed;
    private LocalDateTime dateTime;

    public Todo(Long id, String name) {
        this.id = id;
        this.name = name;
        this.completed = false;
        this.dateTime = LocalDateTime.now();
    }
}
