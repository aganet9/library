package ru.chsu.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import ru.chsu.mapper.BookMapper;
import ru.chsu.mapper.GenreMapper;
import ru.chsu.model.dto.BookDto;
import ru.chsu.model.dto.GenreDto;
import ru.chsu.model.entity.Book;
import ru.chsu.model.entity.Genre;
import ru.chsu.repository.BookRepository;
import ru.chsu.repository.GenreRepository;

import java.util.List;

@ApplicationScoped
public class BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final BookMapper bookMapper;

    @Inject
    public BookService(BookRepository bookRepository, GenreRepository genreRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> getAllBooks() {
        return bookRepository.listAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    public BookDto getBookById(Long id) {
        return bookRepository.findByIdOptional(id)
                .map(bookMapper::toDto)
                .orElseThrow();
    }

    public BookDto createBook(BookDto bookDto) {
        Book book = bookMapper.toEntity(bookDto);
        if (bookDto.getGenresName() != null) {
            book.setGenres(genreRepository.findGenresByNames(bookDto.getGenresName()));
        }

        bookRepository.persist(book);
        return bookMapper.toDto(book);
    }

    public BookDto updateBook(BookDto bookDto) {
        Book book = bookRepository.findByIdOptional(bookDto.getId())
                .orElseThrow();

        bookMapper.updateFromDto(bookDto, book);

        if (bookDto.getGenresName() != null && !bookDto.getGenresName().isEmpty()) {
            book.setGenres(genreRepository.findGenresByNames(bookDto.getGenresName()));
        }

        bookRepository.persist(book);

        return bookMapper.toDto(book);
    }

    public BookDto updateGenres(Long bookId, List<GenreDto> genresDto) {
        Book book = bookRepository.findByIdOptional(bookId)
                .orElseThrow();

        List<String> genreNames = genresDto.stream()
                .map(GenreDto::getName)
                .toList();

        List<Genre> genres = genreRepository.findGenresByNames(genreNames);

        book.setGenres(genres);
        bookRepository.persist(book);
        return bookMapper.toDto(book);
    }

    public BookDto deleteBook(Long bookId) {
        Book book = bookRepository.findByIdOptional(bookId)
                .orElseThrow();
        bookRepository.delete(book);
        return bookMapper.toDto(book);
    }
}
