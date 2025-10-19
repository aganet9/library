package ru.chsu.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import ru.chsu.model.entity.Book;

public class BookRepository implements PanacheRepository<Book> {
}
