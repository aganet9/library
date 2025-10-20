package ru.chsu.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import ru.chsu.model.entity.Genre;

import java.util.List;

@ApplicationScoped
public class GenreRepository implements PanacheRepository<Genre> {
    public List<Genre> findGenresByNames(List<String> names) {
        return list("name IN ?1", names);
    }

    public Genre findGenreByName(String genreName) {
        return this.find("name", genreName).firstResult();
    }
}
