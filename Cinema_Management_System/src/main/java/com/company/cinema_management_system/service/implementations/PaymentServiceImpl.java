package com.company.cinema_management_system.service.implementations;

import com.company.cinema_management_system.entity.Ticket;
import com.company.cinema_management_system.entity.model.SeatType;
import com.company.cinema_management_system.entity.model.dto.AudienceDto;
import com.company.cinema_management_system.entity.model.dto.Response;
import com.company.cinema_management_system.repository.AudienceRepository;
import com.company.cinema_management_system.repository.TicketRepository;
import com.company.cinema_management_system.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final TicketRepository ticketRepository;
    private final AudienceRepository audienceRepository;

    @Override
    public Response payForBookedTicket(AudienceDto audienceDto) {

        boolean exists=audienceRepository.existsByTicket_Seat_InWhichRowAndTicket_Seat_Number(audienceDto.getTicket().getSeat().getInWhichRow(),audienceDto.getTicket().getSeat().getNumber());
        if (exists && audienceDto.getTicket().getSeat().getSeatType().equals(SeatType.RESERVED)) {
           Ticket ticket=ticketRepository.findBySeat_InWhichRowAndSeat_Number(audienceDto.getTicket().getSeat().getInWhichRow(),audienceDto.getTicket().getSeat().getNumber()).get();
           ticket.setPaid(true);
           ticket.getSeat().setSeatType(SeatType.BOUGHT);
           return new Response("Ticket bought successfully",true);
        }
        return new Response("Ticket did not reserved !!! ",false);

    }

    @Override
    public Response payForTicket(AudienceDto audienceDto) {
        boolean exists=ticketRepository.existsBySeat_InWhichRowAndSeat_Number(audienceDto.getTicket().getSeat().getInWhichRow(),audienceDto.getTicket().getSeat().getNumber());
        if (exists) {
        Ticket ticket=ticketRepository.findBySeat_InWhichRowAndSeat_Number(audienceDto.getTicket().getSeat().getInWhichRow(),audienceDto.getTicket().getSeat().getNumber()).get();
  ticket.setPaid(true);
  ticket.getSeat().setSeatType(SeatType.BOUGHT);
  return new Response("Ticket successfully bought",true);
        }

        return new Response("This ticket already exists",false);

    }
}
