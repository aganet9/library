package ru.chsu.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import ru.chsu.exception.BookNotFoundException;
import ru.chsu.exception.BookGenreException;
import ru.chsu.exception.TitleUpdateException;
import ru.chsu.mapper.BookMapper;
import ru.chsu.model.dto.BookDto;
import ru.chsu.model.dto.RequestBook;
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
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Transactional
    public BookDto createBook(RequestBook dto) {
        Book book = bookMapper.toBook(dto);
        genresProcess(book, dto);
        book.setAvailable(true);
        book.setLoans(List.of());

        bookRepository.persist(book);
        return bookMapper.toDto(book);
    }

    @Transactional
    public BookDto updateBook(Long bookId, RequestBook dto) {
        Book book = bookRepository.findByIdOptional(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        bookMapper.updateFromRequestBook(dto, book);

        genresProcess(book, dto);

        bookRepository.persist(book);
        return bookMapper.toDto(book);
    }

    private void genresProcess(Book book, RequestBook dto) {
        if (dto.getGenresName() != null && !dto.getGenresName().isEmpty()) {
            for (Genre genre : List.copyOf(book.getGenres())) {
                book.removeGenre(genre);
            }
            for (String genreName : dto.getGenresName()) {
                Genre genre = genreRepository.findGenreByName(genreName);
                if (genre == null) {
                    genre = new Genre(genreName);
                    genreRepository.persist(genre);
                }
                book.addGenre(genre);
            }
        }
    }

    @Transactional
    public BookDto addGenre(Long bookId, String genreName) {
        if (genreName == null || genreName.trim().isEmpty()) {
            throw new BookGenreException("Genre name is empty");
        }

        Book book = bookRepository.findByIdOptional(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        Genre genre = genreRepository.findGenreByName(genreName);
        if (genre == null) {
            genre = new Genre(genreName);
            genreRepository.persist(genre);
        }

        if (!book.getGenres().contains(genre)) {
            book.addGenre(genre);
        }

        bookRepository.persist(book);
        return bookMapper.toDto(book);
    }

    @Transactional
    public BookDto removeGenre(Long bookId, String genreName) {
        Genre genre = genreRepository.findGenreByName(genreName);
        if (genre == null) {
            throw new BookGenreException("Genre '" + genreName + "' not found");
        }

        Book book = bookRepository.findByIdOptional(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        if (!book.getGenres().contains(genre)) {
            throw new BookGenreException("Book does not contain genre '" + genreName + "'");
        }

        book.removeGenre(genre);
        bookRepository.persist(book);
        return bookMapper.toDto(book);
    }

    @Transactional
    public void deleteBook(Long bookId) {
        Book book = bookRepository.findByIdOptional(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        bookRepository.delete(book);
    }

    @Transactional
    public BookDto changeTitle(Long bookId, String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new TitleUpdateException("Title is empty");
        }
        Book book = bookRepository.findByIdOptional(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        book.setTitle(title);
        bookRepository.persist(book);
        return bookMapper.toDto(book);
    }
}
