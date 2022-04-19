package com.company.cinema_management_system.service;

import com.company.cinema_management_system.entity.Ticket;
import com.lowagie.text.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface PrintTicketService {

    Ticket addTicket(Ticket ticket);

    Ticket get(Integer id);

    List<Ticket> getAll();

    void export(HttpServletResponse response) throws Exception, IOException;

}
