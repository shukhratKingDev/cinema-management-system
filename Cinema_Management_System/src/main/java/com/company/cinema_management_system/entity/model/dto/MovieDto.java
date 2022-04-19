package com.company.cinema_management_system.entity.model.dto;

import com.company.cinema_management_system.entity.Hall;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {
    @NotNull
    private String name;
    @NotNull
    private Integer numberOfTicketsSold;//number of tickets for this movie.
    @NotNull
    private Long durationInMillis;
    @NotNull
    private Timestamp beginDate;
    @NotNull
    private Timestamp sessionDate;
    @NotNull
    private Hall hall;
    private String description;
    @NotNull
    private double price;
}
