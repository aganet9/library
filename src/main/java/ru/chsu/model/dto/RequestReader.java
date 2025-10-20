package ru.chsu.model.dto;

import jakarta.validation.constraints.NotNull;

public class RequestReader {
    @NotNull
    private String name;
    @NotNull
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
