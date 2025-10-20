package ru.chsu.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.chsu.model.dto.ReaderDto;
import ru.chsu.model.entity.Reader;

@Mapper(componentModel = "jakarta", uses = {LoanMapper.class})
public interface ReaderMapper {
    ReaderDto toDto(Reader reader);
    Reader toEntity(ReaderDto reader);
    void updateFromDto(ReaderDto readerDto, @MappingTarget Reader reader);
}
