package com.company.cinema_management_system.entity;

import com.company.cinema_management_system.entity.model.SeatType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.Constraint;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"number","inWhichRow"})})
public class Seat {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Enumerated
    private SeatType seatType;
    private Integer inWhichRow;
    private Integer number;//number in row
}
