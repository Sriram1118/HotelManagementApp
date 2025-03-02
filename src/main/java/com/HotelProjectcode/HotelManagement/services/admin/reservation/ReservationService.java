package com.HotelProjectcode.HotelManagement.services.admin.reservation;

import com.HotelProjectcode.HotelManagement.dto.ReservationResponseDto;

public interface ReservationService {

    ReservationResponseDto getAllReservations(int pageNumber);

    boolean changeReservartionStatus(Long id, String status);

}
