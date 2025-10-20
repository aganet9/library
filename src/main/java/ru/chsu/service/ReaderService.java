package ru.chsu.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import ru.chsu.exception.ReaderLoanException;
import ru.chsu.exception.ReaderNotFoundException;
import ru.chsu.exception.TitleUpdateException;
import ru.chsu.mapper.ReaderMapper;
import ru.chsu.model.dto.ReaderDto;
import ru.chsu.model.dto.RequestReader;
import ru.chsu.model.entity.Loan;
import ru.chsu.model.entity.Reader;
import ru.chsu.repository.ReaderRepository;

import java.util.List;

@ApplicationScoped
public class ReaderService {

    private final ReaderRepository readerRepository;
    private final ReaderMapper readerMapper;

    @Inject
    public ReaderService(ReaderRepository readerRepository, ReaderMapper readerMapper) {
        this.readerRepository = readerRepository;
        this.readerMapper = readerMapper;
    }

    public List<ReaderDto> getAllReaders() {
        return readerRepository.listAll().stream()
                .map(readerMapper::toDto)
                .toList();
    }

    public ReaderDto getReaderById(Long id) {
        return readerRepository.findByIdOptional(id)
                .map(readerMapper::toDto)
                .orElseThrow(() -> new ReaderNotFoundException(id));
    }

    @Transactional
    public ReaderDto createReader(RequestReader dto) {
        Reader reader = readerMapper.toReader(dto);
        readerRepository.persist(reader);
        return readerMapper.toDto(reader);
    }

    @Transactional
    public ReaderDto updateReader(Long id, RequestReader dto) {
        Reader reader = readerRepository.findByIdOptional(id)
                .orElseThrow(() -> new ReaderNotFoundException(id));
        readerMapper.updateFromRequest(dto, reader);
        readerRepository.persist(reader);
        return readerMapper.toDto(reader);
    }

    @Transactional
    public void deleteReader(Long id) {
        Reader reader = readerRepository.findByIdOptional(id)
                .orElseThrow(() -> new ReaderNotFoundException(id));

        if (!reader.getLoans().isEmpty()) {
            throw new ReaderLoanException(id);
        }

        if (reader.getLoans() != null) {
            for (Loan loan : List.copyOf(reader.getLoans())) {
                reader.removeLoan(loan);
            }
        }

        readerRepository.delete(reader);
    }

    @Transactional
    public ReaderDto changeName(Long id, String name) {
        Reader reader = readerRepository.findByIdOptional(id)
                .orElseThrow(() -> new ReaderNotFoundException(id));
        if (name == null || name.trim().isEmpty()) {
            throw new TitleUpdateException("Name is empty");
        }
        reader.setName(name);
        readerRepository.persist(reader);
        return readerMapper.toDto(reader);
    }
}
