package ru.chsu.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ru.chsu.model.dto.LoanDto;
import ru.chsu.model.dto.RequestLoan;
import ru.chsu.model.dto.UpdateLoan;
import ru.chsu.service.LoanService;

import java.util.List;

@Path("/api/loans")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoanController {

    private final LoanService loanService;

    @Inject
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GET
    public List<LoanDto> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GET
    @Path("/{id}")
    public LoanDto getLoanById(@PathParam("id") Long id) {
        return loanService.getLoanById(id);
    }

    @POST
    public LoanDto createLoan(RequestLoan dto){
        return loanService.createLoan(dto);
    }

    @PUT
    @Path("/{id}")
    public LoanDto updateLoan(@PathParam("id") Long id, UpdateLoan dto){
        return loanService.updateLoan(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public void deleteLoan(@PathParam("id") Long id){
        loanService.deleteLoan(id);
    }

    @PATCH
    @Path("/{id}/return")
    public LoanDto returnBook(@PathParam("id") Long loanId){
        return loanService.returnBook(loanId);
    }
}
