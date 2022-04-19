package com.company.cinema_management_system.controller;

import com.company.cinema_management_system.entity.model.dto.AudienceDto;
import com.company.cinema_management_system.entity.model.dto.Response;
import com.company.cinema_management_system.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pay")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/payForTicket")
    public ResponseEntity<Response> payForTicket(AudienceDto audienceDto){
        Response response=paymentService.payForTicket(audienceDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }

    @PostMapping("/payForBookedTicket")
    public ResponseEntity<Response> payForBookedTicket(AudienceDto audienceDto){
        Response response=paymentService.payForBookedTicket(audienceDto);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
    }
}
