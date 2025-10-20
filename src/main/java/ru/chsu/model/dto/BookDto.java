package ru.chsu.model.dto;

import java.util.ArrayList;
import java.util.List;

public class BookDto {
    private Long id;
    private String title;
    private String author;
    private Integer year;
    private boolean available;
    private List<String> genresName = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<String> getGenresName() {
        return genresName;
    }

    public void setGenresName(List<String> genresName) {
        this.genresName = genresName;
    }

    public void addGenreName(String genreName) {
        this.genresName.add(genreName);
    }

    public void removeGenreName(String genreName) {
        this.genresName.remove(genreName);
    }
}
