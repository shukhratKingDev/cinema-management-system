package com.company.cinema_management_system.entity.model.dto;

import com.company.cinema_management_system.entity.Ticket;
import com.company.cinema_management_system.entity.model.RoleTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AudienceDto {
    private Integer id;
    @NotNull
    private String fullName;
    @NotNull
    private String phoneNumber;
    private RoleTypes role;
    @NotNull
    private Ticket ticket;
    private boolean isEnable=true;
}
