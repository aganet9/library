package ru.chsu.model.dto;

import jakarta.validation.constraints.NotNull;

public class RequestLoan {
    @NotNull
    private Long bookId;
    @NotNull
    private Long readerId;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getReaderId() {
        return readerId;
    }

    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }
}
