package com.company.cinema_management_system.controller;

import com.company.cinema_management_system.entity.Audience;
import com.company.cinema_management_system.entity.model.dto.AudienceDto;
import com.company.cinema_management_system.entity.model.dto.Response;
import com.company.cinema_management_system.service.AudienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AudienceController {
    private final AudienceService audienceService;

    @PostMapping("/admin/audience/add")
    public ResponseEntity<Response> add(@Valid @RequestBody AudienceDto audienceDto){
        Response response=audienceService.addAudience(audienceDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(response);
    }

    @PutMapping("/admin/audience/{id}")
    public ResponseEntity<Response> update(@Valid @RequestBody AudienceDto audienceDto, @PathVariable Integer id){
        Response response=audienceService.upDateAudience(audienceDto,id);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.CREATED:HttpStatus.CONFLICT).body(response);
    }

    @DeleteMapping("/audience/{id}")
    public ResponseEntity<Response> delete(@PathVariable Integer id){
        Response response=audienceService.deleteById(id);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping("audience/list")
    public ResponseEntity<List<Audience>> getList(){
        return ResponseEntity.ok(audienceService.getList());
    }

    @GetMapping("audience/{id}")
    public ResponseEntity<Optional<Audience>> get(@PathVariable Integer id){
        return ResponseEntity.ok(audienceService.getById(id));
    }
}
