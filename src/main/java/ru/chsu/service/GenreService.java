package ru.chsu.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import ru.chsu.exception.GenreNotFoundException;
import ru.chsu.mapper.GenreMapper;
import ru.chsu.model.dto.GenreDto;
import ru.chsu.model.dto.RequestGenre;
import ru.chsu.model.entity.Book;
import ru.chsu.model.entity.Genre;
import ru.chsu.repository.GenreRepository;

import java.util.List;

@ApplicationScoped
public class GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Inject
    public GenreService(GenreRepository genreRepository, GenreMapper genreMapper) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
    }

    public List<GenreDto> getAllGenres() {
        return genreRepository.listAll().stream()
                .map(genreMapper::toDto)
                .toList();
    }

    public GenreDto getGenreById(Long id) {
        return genreRepository.findByIdOptional(id)
                .map(genreMapper::toDto)
                .orElseThrow(() -> new GenreNotFoundException(id));
    }

    @Transactional
    public GenreDto createGenre(RequestGenre dto) {
        Genre genre = genreMapper.toGenre(dto);
        genreRepository.persist(genre);
        return genreMapper.toDto(genre);
    }

    @Transactional
    public GenreDto updateGenre(Long id, RequestGenre dto) {
        Genre genre = genreRepository.findByIdOptional(id)
                .orElseThrow(() -> new GenreNotFoundException(id));
        genreMapper.updateFromRequest(dto, genre);
        genreRepository.persist(genre);
        return genreMapper.toDto(genre);
    }

    @Transactional
    public GenreDto patchGenreName(Long id, String name) {
        Genre genre = genreRepository.findByIdOptional(id)
                .orElseThrow(() -> new GenreNotFoundException(id));
        if (name != null) {
            genre.setName(name);
        }
        genreRepository.persist(genre);
        return genreMapper.toDto(genre);
    }

    @Transactional
    public void deleteGenre(Long id) {
        Genre genre = genreRepository.findByIdOptional(id)
                .orElseThrow(() -> new GenreNotFoundException(id));

        if (genre.getBooks() != null) {
            for (Book book : List.copyOf(genre.getBooks())) {
                book.removeGenre(genre);
            }
        }

        genreRepository.delete(genre);
    }
}
