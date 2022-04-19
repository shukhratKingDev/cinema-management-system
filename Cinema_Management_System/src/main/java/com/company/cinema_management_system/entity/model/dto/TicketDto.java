package com.company.cinema_management_system.entity.model.dto;

import com.company.cinema_management_system.entity.Movie;
import com.company.cinema_management_system.entity.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {
    @NotNull
    private Movie movie;
    private Timestamp bookingDate;
    @NotNull
    private Seat seat;
    private boolean isPaid=false;
}
