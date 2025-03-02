package com.HotelProjectcode.HotelManagement.services.customer.booking;

import com.HotelProjectcode.HotelManagement.dto.ReservationDto;
import com.HotelProjectcode.HotelManagement.dto.ReservationResponseDto;

public interface BookingService {

    boolean postReservation(ReservationDto reservationDto);

    ReservationResponseDto getAllReservationByUserId(Long userId, int pageNumber);

}
