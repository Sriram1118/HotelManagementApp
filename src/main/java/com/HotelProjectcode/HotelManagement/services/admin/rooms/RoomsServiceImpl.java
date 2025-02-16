package com.HotelProjectcode.HotelManagement.services.admin.rooms;

import com.HotelProjectcode.HotelManagement.dto.RoomDto;
import com.HotelProjectcode.HotelManagement.entity.Room;
import com.HotelProjectcode.HotelManagement.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomsServiceImpl implements RoomsService {
    private final RoomRepository roomRepository;

    public boolean postRoom(RoomDto roomDto){
        try{
            Room room = new Room();

            room.setName(roomDto.getName());
            room.setType(roomDto.getType());
            room.setPrice(roomDto.getPrice());
            room.setAvailable(roomDto.getAvailable());

            roomRepository.save(room);
            return true;

        }catch (Exception e){
            return false;
        }
    }
}
