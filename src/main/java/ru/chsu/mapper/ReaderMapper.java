package ru.chsu.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.chsu.model.dto.ReaderDto;
import ru.chsu.model.dto.RequestReader;
import ru.chsu.model.entity.Reader;

@Mapper(componentModel = "jakarta", uses = {LoanMapper.class})
public interface ReaderMapper {
    ReaderDto toDto(Reader reader);

    Reader toEntity(ReaderDto reader);

    void updateFromDto(ReaderDto readerDto, @MappingTarget Reader reader);

    RequestReader toRequest(Reader reader);

    @Mapping(target = "loans", ignore = true)
    @Mapping(target = "id", ignore = true)
    Reader toReader(RequestReader reader);

    @Mapping(target = "loans", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateFromRequest(RequestReader requestReader, @MappingTarget Reader reader);
}
