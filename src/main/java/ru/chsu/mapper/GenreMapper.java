package ru.chsu.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import ru.chsu.model.dto.GenreDto;
import ru.chsu.model.entity.Book;
import ru.chsu.model.entity.Genre;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface GenreMapper {
    @Mapping(target = "bookIds", source = "books", qualifiedByName = "mapBooksToIds")
    GenreDto toDto(Genre genre);
    @Mapping(target = "books", ignore = true )
    Genre toEntity(GenreDto genreDto);
    @Mapping(target = "books", ignore = true)
    void updateFromDto(GenreDto genreDto, @MappingTarget Genre genre);

    @Named("mapBooksToIds")
    default List<Long> mapBooksToIds(List<Book> books) {
        return books == null ? null : books.stream()
                .map(Book::getId)
                .toList();
    }
}
