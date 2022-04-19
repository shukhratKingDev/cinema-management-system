package com.company.cinema_management_system.service;

import com.company.cinema_management_system.entity.Manager;
import com.company.cinema_management_system.entity.model.dto.ManagerDto;
import com.company.cinema_management_system.entity.model.dto.Response;

import java.util.List;
import java.util.Optional;

public interface ManagerService {
    Response addManager(ManagerDto managerDto);
    Response updateManager(ManagerDto managerDto,Integer id);
    Response deleteManager(Integer id);
    List<Manager> getList();
    Optional<Manager> getById(Integer id);
    Response deleteAll();
}
