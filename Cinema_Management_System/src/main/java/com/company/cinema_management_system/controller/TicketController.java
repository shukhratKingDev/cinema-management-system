package com.company.cinema_management_system.controller;

import com.company.cinema_management_system.entity.Movie;
import com.company.cinema_management_system.entity.Ticket;
import com.company.cinema_management_system.entity.model.dto.MovieDto;
import com.company.cinema_management_system.entity.model.dto.Response;
import com.company.cinema_management_system.entity.model.dto.TicketDto;
import com.company.cinema_management_system.service.MovieService;
import com.company.cinema_management_system.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/admin/ticket/add")
    public ResponseEntity<Response> add(@Valid @RequestBody TicketDto ticketDto){
        Response response=ticketService.add(ticketDto);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.CREATED:HttpStatus.CONFLICT).body(response);
    }

    @PutMapping("/admin/ticket/{id}")
    public ResponseEntity<Response> update(@Valid @RequestBody TicketDto ticketDto,@PathVariable Integer id){
        Response response=ticketService.update(ticketDto,id);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(response);
    }

    @DeleteMapping("/admin/ticket/{id}")
    public ResponseEntity<Response> delete(@PathVariable Integer id){
        Response response=ticketService.deleteById(id);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping("/ticket/{id}")
    public ResponseEntity<Optional<Ticket>> getById(@PathVariable Integer id){
        Optional<Ticket> ticket=ticketService.getById(id);
        return ResponseEntity.status(ticket.isPresent()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(ticket);

    }
    @GetMapping("/ticket/list")
    public ResponseEntity<List<Ticket>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(ticketService.getList());
    }
}
