package com.HotelProjectcode.HotelManagement.services.auth;

import com.HotelProjectcode.HotelManagement.dto.SignupRequest;
import com.HotelProjectcode.HotelManagement.dto.UserDto;

//@Service
public interface AuthService {

    UserDto createUser(SignupRequest signupRequest);

}
