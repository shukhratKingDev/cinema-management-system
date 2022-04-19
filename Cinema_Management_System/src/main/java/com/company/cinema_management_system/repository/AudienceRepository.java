package com.company.cinema_management_system.repository;

import com.company.cinema_management_system.entity.Audience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudienceRepository extends JpaRepository<Audience,Integer> {
    boolean existsByTicket_Seat_InWhichRowAndTicket_Seat_Number(Integer ticket_seat_inWhichRow, Integer ticket_seat_number);
}
