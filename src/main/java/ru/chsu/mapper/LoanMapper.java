package ru.chsu.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import ru.chsu.model.dto.LoanDto;
import ru.chsu.model.entity.Book;
import ru.chsu.model.entity.Loan;
import ru.chsu.model.entity.Reader;

@Mapper(componentModel = "cdi")
public interface LoanMapper {
    @Mapping(target = "readerName", source = "reader.name")
    @Mapping(target = "readerId", source = "reader.id")
    @Mapping(target = "bookTitle", source = "book.title")
    @Mapping(target = "bookId", source = "book.id")
    LoanDto toDto(Loan loan);
    @Mapping(target = "reader", qualifiedByName = "mapReaderId")
    @Mapping(target = "book", qualifiedByName = "mapBookId")
    Loan toEntity(LoanDto loanDto);
    @Mapping(target = "reader", qualifiedByName = "mapReaderId")
    @Mapping(target = "book", qualifiedByName = "mapBookId")
    void updateFromDto(LoanDto loanDto, @MappingTarget Loan loan);

    @Named("mapReaderId")
    default Reader mapReaderId(LoanDto loanDto) {
        if (loanDto.getReaderId() == null) {
            return null;
        }
        Reader reader = new Reader();
        reader.setId(loanDto.getReaderId());
        return  reader;
    }

    @Named("mapBookId")
    default Book mapBookId(LoanDto loanDto) {
        if (loanDto.getBookId() == null) {
            return null;
        }
        Book book = new Book();
        book.setId(loanDto.getBookId());
        return book;
    }
}
