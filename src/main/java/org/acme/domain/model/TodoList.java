package org.acme.domain.model;

import java.util.Set;
import java.util.UUID;

public class TodoList {
    private UUID id;
    private String name;
    private Set<Todo> todoSet;
    private UUID ownerId;

    public TodoList() {
    }

    public static Builder builder() { return new Builder();}

    public static class Builder {
        private UUID id;
        private String name;
        private Set<Todo> todoSet;
        private UUID ownerId;

        public Builder id(UUID id){
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder todoSet(Set<Todo> todoSet){
            this.todoSet = todoSet;
            return this;
        }

        public Builder ownerId(UUID ownerId){
            this.ownerId = ownerId;
            return this;
        }

        public TodoList build(){
            TodoList todoList = new TodoList();
            todoList.id = this.id;
            todoList.name = this.name;
            todoList.todoSet = this.todoSet;
            todoList.ownerId = this.ownerId;
            return todoList;
        }
    }

    public TodoList(UUID id, String name, Set<Todo> todoSet, UUID ownerId) {
        this.id = id;
        this.name = name;
        this.todoSet = todoSet;
        this.ownerId = ownerId;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Todo> getTodoSet() {
        return todoSet;
    }

    public UUID getOwnerId() {
        return ownerId;
    }
}
