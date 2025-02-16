package com.HotelProjectcode.HotelManagement.dto;

import com.HotelProjectcode.HotelManagement.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String email;

    private String name;

    private UserRole userRole;
}
