package com.company.cinema_management_system.controller;

import com.company.cinema_management_system.entity.Ticket;
import com.company.cinema_management_system.repository.TicketRepository;
import com.company.cinema_management_system.service.PrintTicketService;
import com.lowagie.text.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ticket/print")
public class PrintTicketController {

    private final PrintTicketService ticketService;
    private final TicketRepository ticketRepository;

    @PostMapping("/add")
    public ResponseEntity<Ticket> addTicket(@RequestBody Ticket ticket){
        Ticket ticket1 = ticketService.addTicket(ticket);
        if (!(ticket1==null)) {
            ResponseEntity.status(HttpStatus.OK).body(ticket1);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

    }

    @GetMapping("/{id}")
    public void exportToPDF(HttpServletResponse response, @PathVariable Integer id) throws Exception, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Ticket> ticketList = ticketService.getAll();

        ticketService.export(response);

    }

}
