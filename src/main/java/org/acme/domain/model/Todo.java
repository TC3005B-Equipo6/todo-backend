package org.acme.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Todo {
    private UUID id;
    private String title;
    private String description;
    private boolean completed;
    private LocalDateTime dueDate;
    private LocalDateTime createdAt;
    private UUID listId;
    private UUID ownerId;

    public Todo() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private String title;
        private String description;
        private boolean completed;
        private LocalDateTime dueDate;
        private LocalDateTime createdAt;
        private UUID listId;
        private UUID ownerId;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description){
            this.description = description;
            return this;
        }

        public Builder completed(boolean completed){
            this.completed = completed;
            return this;
        }

        public Builder dueDate(LocalDateTime dueDate){
            this.dueDate = dueDate;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt){
            this.createdAt = createdAt;
            return this;
        }

        public Builder listId(UUID listId){
            this.listId = listId;
            return this;
        }

        public Builder ownerId(UUID ownerId){
            this.ownerId = ownerId;
            return this;
        }

        public Todo build(){
            Todo todo = new Todo();
            todo.id = this.id;
            todo.title = this.title;
            todo.description = this.description;
            todo.completed = this.completed;
            todo.dueDate = this.dueDate;
            todo.createdAt = this.createdAt;
            todo.listId = this.listId;
            todo.ownerId = this.ownerId;
            return todo;
        }

    }

    public Todo(UUID id, String title, String description, boolean completed, LocalDateTime dueDate, LocalDateTime createdAt, UUID listId, UUID ownerId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
        this.listId = listId;
        this.ownerId = ownerId;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public UUID getListId() {
        return listId;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                ", createdAt=" + createdAt +
                '}';
    }
}