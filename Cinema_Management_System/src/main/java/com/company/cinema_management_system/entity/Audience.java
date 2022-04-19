package com.company.cinema_management_system.entity;

import com.company.cinema_management_system.entity.model.RoleTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Audience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false,unique = true)
    private String phoneNumber;
    @Column(nullable = false)
    private RoleTypes role;
    @OneToOne(cascade ={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private Ticket ticket;
    private boolean isEnable=true;
}
