package ru.chsu.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import ru.chsu.model.entity.Reader;

@ApplicationScoped
public class ReaderRepository implements PanacheRepository<Reader> {
}
