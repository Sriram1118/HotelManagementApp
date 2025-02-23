package com.HotelProjectcode.HotelManagement.services.admin.rooms;

import com.HotelProjectcode.HotelManagement.dto.RoomDto;
import com.HotelProjectcode.HotelManagement.dto.RoomsResponseDto;

public interface RoomsService {

    boolean postRoom(RoomDto roomDto);

    RoomsResponseDto getAllRooms(int pageNumber);

    RoomDto getRoomById(Long id);

    boolean updateRoom(Long id, RoomDto roomDto);

    void deleteRoom(Long id);

}
