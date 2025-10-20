package ru.chsu.controller;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ru.chsu.model.dto.GenreDto;
import ru.chsu.model.dto.RequestGenre;
import ru.chsu.service.GenreService;

import java.util.List;

@Path("/api/genres")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GenreController {

    private final GenreService genreService;

    @Inject
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GET
    public List<GenreDto> getAllGenres() {
        return genreService.getAllGenres();
    }

    @GET
    @Path("/{id}")
    public GenreDto getGenreById(@PathParam("id") Long id) {
        return genreService.getGenreById(id);
    }

    @POST
    public GenreDto createGenre(@Valid RequestGenre requestGenre) {
        return genreService.createGenre(requestGenre);
    }

    @PUT
    @Path("/{id}")
    public GenreDto updateGenre(@PathParam("id") Long id, @Valid RequestGenre requestGenre) {
        return genreService.updateGenre(id, requestGenre);
    }

    @DELETE
    @Path("/{id}")
    public void deleteGenre(@PathParam("id") Long id) {
        genreService.deleteGenre(id);
    }
}
