package com.HotelProjectcode.HotelManagement.controller.admin;

import com.HotelProjectcode.HotelManagement.services.admin.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/reservations/{pageNumber}")
    public ResponseEntity<?> getAllReservations(@PathVariable int pageNumber){
        try{
            return ResponseEntity.ok(reservationService.getAllReservations(pageNumber));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong");
        }
    }

    @GetMapping("reservations/{id}/{status}")
    public ResponseEntity<?> changeReservationStatus(@PathVariable Long id,@PathVariable String status){
        boolean success = reservationService.changeReservartionStatus(id,status);

        if(success){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }
}
