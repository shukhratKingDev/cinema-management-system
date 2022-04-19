package com.company.cinema_management_system.service.implementations;

import com.company.cinema_management_system.entity.Hall;
import com.company.cinema_management_system.entity.Movie;
import com.company.cinema_management_system.entity.Seat;
import com.company.cinema_management_system.entity.Ticket;
import com.company.cinema_management_system.entity.model.SeatType;
import com.company.cinema_management_system.entity.model.dto.MovieDto;
import com.company.cinema_management_system.entity.model.dto.Response;
import com.company.cinema_management_system.entity.model.dto.TicketDto;
import com.company.cinema_management_system.repository.MovieRepository;
import com.company.cinema_management_system.repository.TicketRepository;
import com.company.cinema_management_system.service.MovieService;
import com.company.cinema_management_system.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;


    @Override
    public Response add(TicketDto ticketDto) {
boolean exists=ticketRepository.existsBySeat_InWhichRowAndSeat_Number(ticketDto.getSeat().getInWhichRow(),ticketDto.getSeat().getNumber());
if (exists){
    return new Response("This Ticket is already added",false);
}
        Ticket ticket=new Ticket();

ticketRepository.save(parse(ticketDto,ticket));
return new Response("Ticket saved successfully !!!",true);
    }

    @Override
    public Response update(TicketDto ticketDto,Integer id) {
boolean exist=ticketRepository.existsBySeat_InWhichRowAndSeat_NumberAndIdNot(ticketDto.getSeat().getInWhichRow(),ticketDto.getSeat().getNumber(),id);
        if (!exist) {
            return new Response("Ticket not found",false);
        }
         Ticket ticket=ticketRepository.getById(id);
        ticketRepository.save(parse(ticketDto,ticket));
         return new Response("Ticket successfully updated",true);

    }

    @Override
    public List<Ticket> getList() {
        return ticketRepository.findAll();
    }

    @Override
    public Optional<Ticket> getById(Integer id) {
       return check(id);
    }

    @Override
    public Response deleteById(Integer id) {
       ticketRepository.deleteById(id);
       return new Response("Ticket deleted successfully !!!",true);

    }

    public Ticket parse(TicketDto ticketDto,Ticket ticket){
      ticket.setBookingDate(ticketDto.getBookingDate());
        Seat seat=new Seat();
        seat.setSeatType(SeatType.FREE);
        seat.setInWhichRow(ticketDto.getSeat().getInWhichRow());
        seat.setNumber(ticketDto.getSeat().getNumber());
      ticket.setSeat(seat);
     Movie movie=new Movie();
        movie.setName(ticketDto.getMovie().getName());
        movie.setNumberOfTicketsSold(ticketDto.getMovie().getNumberOfTicketsSold());
        movie.setDurationInMillis(ticketDto.getMovie().getDurationInMillis());
        movie.setBeginDate(ticketDto.getMovie().getBeginDate());
        movie.setSessionDate(ticketDto.getMovie().getSessionDate());
        Hall hall=new Hall();
        hall.setName(ticketDto.getMovie().getHall().getName());
        hall.setNumberOfRows(ticketDto.getMovie().getHall().getNumberOfRows());
        hall.setNumberOfSeatsInRow(ticketDto.getMovie().getHall().getNumberOfSeatsInRow());

        movie.setHall(hall);
        movie.setDescription(ticketDto.getMovie().getDescription());
        movie.setPrice(ticketDto.getMovie().getPrice());
        ticket.setMovie(movie);

        return ticket;
    }

    public Optional<Ticket> check(Integer id){
        Optional<Ticket> ticket=ticketRepository.findById(id);
        if (ticket.isPresent()) {
            return ticket;
    }
        return  null;
    }
}
