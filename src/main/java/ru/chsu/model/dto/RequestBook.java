package ru.chsu.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RequestBook {
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @NotNull
    private Integer year;
    private List<String> genresName = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<String> getGenresName() {
        return genresName;
    }

    public void setGenresName(List<String> genresName) {
        this.genresName = genresName;
    }
}
