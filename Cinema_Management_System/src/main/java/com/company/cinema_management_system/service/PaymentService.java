package com.company.cinema_management_system.service;

import com.company.cinema_management_system.entity.model.dto.AudienceDto;
import com.company.cinema_management_system.entity.model.dto.Response;
import com.company.cinema_management_system.entity.model.dto.TicketDto;

public interface PaymentService {
    Response payForBookedTicket(AudienceDto audienceDto);// Paying for already reserved ticket.
    Response payForTicket(AudienceDto audienceDto);// Going to the cinema and bought non-reserved ticket
}
