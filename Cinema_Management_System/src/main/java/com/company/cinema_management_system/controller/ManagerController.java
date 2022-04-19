package com.company.cinema_management_system.controller;

import com.company.cinema_management_system.entity.Manager;
import com.company.cinema_management_system.entity.model.dto.ManagerDto;
import com.company.cinema_management_system.entity.model.dto.Response;
import com.company.cinema_management_system.service.ManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ManagerController {
    ManagerService managerService;

    @PostMapping("/admin/manager/add")
    public ResponseEntity<Response> addManager(@Valid @RequestBody ManagerDto managerDto){
        Response response=managerService.addManager(managerDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(response);
    }

    @PutMapping("/admin/manager/{id}")
    public ResponseEntity<Response> update(@Valid @RequestBody ManagerDto managerDto,@PathVariable Integer id){
        Response response= managerService.updateManager(managerDto,id);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(response);
    }

    @GetMapping("/manager/list")
    public ResponseEntity<List<Manager>> getList(){
        return ResponseEntity.status(HttpStatus.OK).body(managerService.getList());
    }

    @GetMapping("/manager/{id}")
    public ResponseEntity<Optional<Manager>> getById(@PathVariable Integer id){
        Optional<Manager> manager=managerService.getById(id);
        if(manager.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(manager);
    }

    @DeleteMapping("/admin/manager/{id}")
    public ResponseEntity<Response> delete(@PathVariable Integer id){
        Response response=managerService.deleteManager(id);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/admin/manager/deleteAll")//after movie started or ended manager can delete all tickets for this movie from database.
    public ResponseEntity<Response> deleteAll(){
        Response response = managerService.deleteAll();
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }
}
