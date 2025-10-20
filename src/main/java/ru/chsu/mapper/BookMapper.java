package ru.chsu.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import ru.chsu.model.dto.BookDto;
import ru.chsu.model.entity.Book;
import ru.chsu.model.entity.Genre;

import java.util.List;

@Mapper(componentModel = "cdi", uses = {GenreMapper.class})
public interface BookMapper {
    @Mapping(target = "genresName", source = "genres", qualifiedByName = "mapGenresToNames")
    BookDto toDto(Book book);
    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "loans", ignore = true)
    Book toEntity(BookDto bookDto);
    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "loans", ignore = true)
    void updateFromDto(BookDto bookDto, @MappingTarget Book book);

    @Named("mapGenresToNames")
    default List<String> mapGenresToNames(List<Genre> genres) {
        return genres == null ? null : genres.stream()
                .map(Genre::getName)
                .toList();
    }
}
