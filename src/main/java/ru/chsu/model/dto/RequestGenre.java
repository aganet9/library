package ru.chsu.model.dto;

import jakarta.validation.constraints.NotNull;

public class RequestGenre {
    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
