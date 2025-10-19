package ru.chsu.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import ru.chsu.model.entity.Genre;

public class GenreRepository implements PanacheRepository<Genre> {
}
