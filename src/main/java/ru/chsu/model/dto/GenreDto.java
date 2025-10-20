package ru.chsu.model.dto;

import java.util.ArrayList;
import java.util.List;

public class GenreDto {
    private Long id;
    private String name;
    private List<Long> bookIds = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getBookIds() {
        return bookIds;
    }

    public void setBookIds(List<Long> bookIds) {
        this.bookIds = bookIds;
    }

    public void addBookId(Long bookId) {
        this.bookIds.add(bookId);
    }

    public void removeBookId(Long bookId) {
        this.bookIds.remove(bookId);
    }
}
