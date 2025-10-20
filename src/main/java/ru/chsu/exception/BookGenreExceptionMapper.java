package ru.chsu.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Map;

@Provider
public class BookGenreExceptionMapper implements ExceptionMapper<BookGenreException> {
    @Override
    public Response toResponse(BookGenreException genreException) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(Map.of("message", genreException.getMessage()))
                .build();
    }
}
