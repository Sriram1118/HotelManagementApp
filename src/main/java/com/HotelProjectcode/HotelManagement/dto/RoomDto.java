package com.HotelProjectcode.HotelManagement.dto;

import lombok.Data;

@Data
public class RoomDto {

    private Long id;

    private String name;

    private String type;

    private Long price;

    private Boolean available;

}
