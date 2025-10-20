package ru.chsu.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

import java.util.Map;

public class BookUnavailableExceptionMapper implements ExceptionMapper<BookUnavailableException> {
    @Override
    public Response toResponse(BookUnavailableException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(Map.of("message", e.getMessage()))
                .build();
    }
}
