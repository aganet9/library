package ru.chsu.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ru.chsu.model.dto.ReaderDto;
import ru.chsu.model.dto.RequestReader;
import ru.chsu.service.ReaderService;

import java.util.List;

@Path("/api/readers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReaderController {

    private final ReaderService readerService;

    @Inject
    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GET
    public List<ReaderDto> getAllReaders() {
        return readerService.getAllReaders();
    }

    @GET
    @Path("/{id}")
    public ReaderDto getReaderById(@PathParam("id") Long id) {
        return readerService.getReaderById(id);
    }

    @POST
    public ReaderDto createReader(RequestReader dto){
        return readerService.createReader(dto);
    }

    @PUT
    @Path("/{id}")
    public ReaderDto updateReader(@PathParam("id") Long id, RequestReader dto){
        return readerService.updateReader(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public void deleteReaderById(@PathParam("id") Long id){
        readerService.deleteReaderById(id);
    }

    @PATCH
    @Path("/{id}")
    public ReaderDto changeName(@PathParam("id") Long id, @QueryParam("name") String name){
        return readerService.changeName(id, name);
    }

}
