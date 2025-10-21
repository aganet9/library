package ru.chsu.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import ru.chsu.model.entity.Genre;

@ApplicationScoped
public class GenreRepository implements PanacheRepository<Genre> {
    public Genre findGenreByName(String genreName) {
        return this.find("name", genreName).firstResult();
    }
}
