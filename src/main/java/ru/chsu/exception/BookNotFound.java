package ru.chsu.exception;

public class BookNotFound extends RuntimeException {
    public BookNotFound(Long bookId)  {
        super("Book with id " + bookId + " not found");
    }
}
