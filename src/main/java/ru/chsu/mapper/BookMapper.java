package ru.chsu.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import ru.chsu.model.dto.BookDto;
import ru.chsu.model.dto.RequestBook;
import ru.chsu.model.entity.Book;
import ru.chsu.model.entity.Genre;
import ru.chsu.model.entity.Loan;

import java.util.List;

@Mapper(componentModel = "jakarta", uses = {GenreMapper.class})
public interface BookMapper {
    @Mapping(target = "loansCount", source = "loans", qualifiedByName = "mapLoansToCount")
    @Mapping(target = "genresName", source = "genres", qualifiedByName = "mapGenresToNames")
    BookDto toDto(Book book);

    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "loans", ignore = true)
    Book toEntity(BookDto bookDto);

    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "loans", ignore = true)
    void updateFromDto(BookDto bookDto, @MappingTarget Book book);

    @Mapping(target = "genresName", source = "genres", qualifiedByName = "mapGenresToNames")
    RequestBook toRequestBook(Book book);

    @Mapping(target = "loans", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "available", ignore = true)
    Book toBook(RequestBook requestBook);

    @Mapping(target = "loans", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "available", ignore = true)
    void updateFromRequestBook(RequestBook requestBook, @MappingTarget Book book);

    @Named("mapGenresToNames")
    default List<String> mapGenresToNames(List<Genre> genres) {
        return genres == null ? null : genres.stream()
                .map(Genre::getName)
                .toList();
    }

    @Named("mapLoansToCount")
    default Integer mapLoansToCount(List<Loan> loans) {
        return loans == null ? null : loans.size();
    }
}
