package com.company.cinema_management_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movie {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false,unique = true)
    private String name;
    private Integer numberOfTicketsSold;
    @Column(nullable = false)
    private Long durationInMillis;
    @Column(nullable = false)
    private Timestamp beginDate;
    private Timestamp sessionDate;
    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE})
    private Hall hall;
    private String description;
    @Column(nullable = false)
    private double price;
}
