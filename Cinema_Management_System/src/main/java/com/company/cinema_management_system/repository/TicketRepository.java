package com.company.cinema_management_system.repository;

import com.company.cinema_management_system.entity.Seat;
import com.company.cinema_management_system.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer> {
    boolean existsBySeat_InWhichRowAndSeat_Number(Integer seat_inWhichRow, Integer seat_number);
    boolean existsBySeat_InWhichRowAndSeat_NumberAndIdNot(Integer seat_inWhichRow, Integer seat_number, Integer id);
    Optional<Ticket> findBySeat_InWhichRowAndSeat_Number(Integer seat_inWhichRow, Integer seat_number);
}
