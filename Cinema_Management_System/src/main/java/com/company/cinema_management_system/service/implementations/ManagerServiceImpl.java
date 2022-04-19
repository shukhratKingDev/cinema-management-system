package com.company.cinema_management_system.service.implementations;

import com.company.cinema_management_system.entity.Manager;
import com.company.cinema_management_system.entity.model.RoleTypes;
import com.company.cinema_management_system.entity.model.dto.ManagerDto;
import com.company.cinema_management_system.entity.model.dto.Response;
import com.company.cinema_management_system.repository.ManagerRepository;
import com.company.cinema_management_system.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl  implements ManagerService {
    private final ManagerRepository managerRepository;

    @Override
    public Response deleteAll() {
        return null;
    }

    @Override
    public Response addManager(ManagerDto managerDto) {
        boolean exists=managerRepository.existsByUsername(managerDto.getUsername());
        if (exists){
            return new Response("This manager is already exists ",false);
        }
        Manager manager=new Manager();
        managerRepository.save(parse(managerDto,manager));
        return new Response("Manager added successfully !!!",true);
    }

    @Override
    public Response updateManager(ManagerDto managerDto, Integer id) {
        Optional<Manager>manager=managerRepository.findById(id);
        if (manager.isPresent()) {
            managerRepository.save(parse(managerDto,manager.get()));
            return new Response("Manager updated successfully ",true);
        }
        return new Response("Manager not found",false);
    }

    @Override
    public Response deleteManager(Integer id) {
        Optional<Manager> manager =managerRepository.findById(id);
        if (manager.isPresent()) {
            managerRepository.deleteById(id);
            return new Response("Manager deleted ",true);
        }
        return new Response("Manager not found ",false);
    }

    @Override
    public List<Manager> getList() {
        return managerRepository.findAll();
    }

    @Override
    public Optional<Manager> getById(Integer id) {
        Optional<Manager> manager =managerRepository.findById(id);
        if (manager.isPresent()) {
            return managerRepository.findById(id);
        }
        return null;
    }


    public Manager parse(ManagerDto managerDto,Manager manager) {
        manager.setFullName(managerDto.getFullName());
        manager.setUsername(managerDto.getUsername());
        manager.setRoles(RoleTypes.MANAGER);
        manager.setPassword(managerDto.getPassword());

        return manager;
    }

}
