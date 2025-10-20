package ru.chsu.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Map;

@Provider
public class ReaderNotFoundExceptionMapper implements ExceptionMapper<ReaderNotFoundException> {
    @Override
    public Response toResponse(ReaderNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(Map.of("error", "Not found",
                        "message", e.getMessage()))
                .build();
    }
}
