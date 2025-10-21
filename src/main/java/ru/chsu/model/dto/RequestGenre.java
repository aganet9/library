package ru.chsu.model.dto;

import jakarta.validation.constraints.NotBlank;

public class RequestGenre {
    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
