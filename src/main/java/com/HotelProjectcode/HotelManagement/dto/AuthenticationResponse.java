package com.HotelProjectcode.HotelManagement.dto;

import com.HotelProjectcode.HotelManagement.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {

    private String jwt;
    private Long userId;
    private UserRole userRole;
}
