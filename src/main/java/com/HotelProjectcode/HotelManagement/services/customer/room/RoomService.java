package com.HotelProjectcode.HotelManagement.services.customer.room;

import com.HotelProjectcode.HotelManagement.dto.RoomsResponseDto;

public interface RoomService {

    RoomsResponseDto getAvailableRooms(int pageNumber);

}
