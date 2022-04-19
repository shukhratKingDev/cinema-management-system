package com.company.cinema_management_system.service;

import com.company.cinema_management_system.entity.Movie;
import com.company.cinema_management_system.entity.Ticket;
import com.company.cinema_management_system.entity.model.dto.MovieDto;
import com.company.cinema_management_system.entity.model.dto.Response;
import com.company.cinema_management_system.entity.model.dto.TicketDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    Response add(TicketDto ticketDto);

    Response update(TicketDto ticketDto, Integer id);

    List<Ticket> getList();

    Optional<Ticket> getById(Integer id);

    Response deleteById(Integer id);
}
