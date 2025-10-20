package ru.chsu.exception;

public class ReaderLoanException extends RuntimeException {
    public ReaderLoanException(Long id) {
        super("Reader " + id + " has a loan");
    }
}
