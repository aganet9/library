package ru.chsu.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import ru.chsu.model.dto.LoanDto;
import ru.chsu.model.entity.Book;
import ru.chsu.model.entity.Loan;
import ru.chsu.model.entity.Reader;

@Mapper(componentModel = "jakarta")
public interface LoanMapper {
    @Mapping(target = "readerName", source = "reader.name")
    @Mapping(target = "readerId", source = "reader.id")
    @Mapping(target = "bookTitle", source = "book.title")
    @Mapping(target = "bookId", source = "book.id")
    LoanDto toDto(Loan loan);
    @Mapping(target = "reader", source = "readerId", qualifiedByName = "mapReaderId")
    @Mapping(target = "book", source = "bookId", qualifiedByName = "mapBookId")
    Loan toEntity(LoanDto loanDto);
    @Mapping(target = "reader", source = "readerId", qualifiedByName = "mapReaderId")
    @Mapping(target = "book", source = "bookId", qualifiedByName = "mapBookId")
    void updateFromDto(LoanDto loanDto, @MappingTarget Loan loan);

    @Named("mapReaderId")
    default Reader mapReaderId(Long readerId) {
        if (readerId == null) return null;
        Reader reader = new Reader();
        reader.setId(readerId);
        return reader;
    }

    @Named("mapBookId")
    default Book mapBookId(Long bookId) {
        if (bookId == null) return null;
        Book book = new Book();
        book.setId(bookId);
        return book;
    }
}
