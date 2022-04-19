package com.company.cinema_management_system.service.implementations;

import com.company.cinema_management_system.entity.Audience;
import com.company.cinema_management_system.entity.Ticket;
import com.company.cinema_management_system.entity.model.RoleTypes;
import com.company.cinema_management_system.entity.model.SeatType;
import com.company.cinema_management_system.entity.model.dto.AudienceDto;
import com.company.cinema_management_system.entity.model.dto.Response;
import com.company.cinema_management_system.repository.AudienceRepository;
import com.company.cinema_management_system.repository.TicketRepository;
import com.company.cinema_management_system.service.AudienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AudienceServiceImpl implements AudienceService {
    private final TicketRepository ticketRepository;
    private final AudienceRepository audienceRepository;

    @Override
    public Response addAudience(AudienceDto audienceDto) {
        Audience audience = new Audience();
        Ticket ticket = audienceDto.getTicket();
        if (check(ticket)) {
            audienceDto.getTicket().getSeat().setSeatType(SeatType.RESERVED);
            audienceDto.getTicket().setBookingDate(Timestamp.valueOf(LocalDateTime.now()));
            audience.setTicket(audienceDto.getTicket());

            audienceRepository.save(audience);
            return new Response("Audience saved successfully !!!", true);
        }

        return new Response("Audience did not saved ", false);
    }

    @Override
    public Response upDateAudience(AudienceDto audienceDto, Integer id) {
        Optional<Audience> audience = audienceRepository.findById(id);
        if (audience.isPresent()) {
            if (Objects.equals(audienceDto.getTicket().getSeat().getInWhichRow(), audience.get().getTicket().getSeat().getInWhichRow())) {
                if (Objects.equals(audienceDto.getTicket().getSeat().getNumber(), audience.get().getTicket().getSeat().getNumber())) {
                    audienceRepository.save(parse(audienceDto, audience.get()));
                    return new Response("Audience updated", true);
                } else {
                    if (check(audienceDto.getTicket())) {
                        audienceRepository.save(parse(audienceDto, audience.get()));
                    }
                }
            }
        }
        return new Response("Audience did not update !!!", false);

    }

    @Override
    public List<Audience> getList() {
        return audienceRepository.findAll();
    }

    @Override
    public Optional<Audience> getById(Integer id) {
        return audienceRepository.findById(id);
    }

    @Override
    public Response deleteById(Integer id) {
        Audience audience = audienceRepository.getById(id);
        audience.getTicket().getSeat().setSeatType(SeatType.FREE);
        audienceRepository.deleteById(id);
        return new Response("Success", true);
    }

    public Audience parse(AudienceDto audienceDto, Audience audience) {
        audience.setFullName(audienceDto.getFullName());
        audience.setPhoneNumber(audienceDto.getPhoneNumber());
        audience.setRole(RoleTypes.USER);

        return audience;
    }

    public boolean check(Ticket ticket) {
        if (ticketRepository.existsById(ticket.getId())) {
            if (ticket.isPaid()) {
                return false;
            }

            if (ticket.getSeat().getSeatType().toString().equalsIgnoreCase(SeatType.FREE.toString())) {
                return true;
            } else if (ticket.getSeat().getSeatType().toString().equals(SeatType.RESERVED.toString())) {
                if (ticket.getMovie().getBeginDate().getTime() - System.currentTimeMillis() >= 1000 * 60 * 60 * 2) {
                    return false;
                } else {
                    ticket.getSeat().setSeatType(SeatType.FREE);
                    return true;
                }
            }
        }

        return false;
    }

}
