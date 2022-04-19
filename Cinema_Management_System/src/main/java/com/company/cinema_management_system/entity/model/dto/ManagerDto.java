package com.company.cinema_management_system.entity.model.dto;

import com.company.cinema_management_system.entity.model.RoleTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerDto {
    @NotNull
    private String fullName;
    @NotNull
    private String username;
    @NotNull
    private String password;
    private RoleTypes roles;
    private boolean isEnable=true;
}
