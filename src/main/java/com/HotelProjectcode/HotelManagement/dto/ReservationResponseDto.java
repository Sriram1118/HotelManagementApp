package com.HotelProjectcode.HotelManagement.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReservationResponseDto {

    private Integer totalPages;

    private List<ReservationDto> reservationDtoList;

    private Integer pageNumber;

}
