package com.HotelProjectcode.HotelManagement.entity;

import com.HotelProjectcode.HotelManagement.dto.RoomDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    private Long price;

    private Boolean available = true; //to-do - false needed ?

    public RoomDto getRoomDto(){
        RoomDto roomDto = new RoomDto();

        roomDto.setAvailable(available);
        roomDto.setType(type);
        roomDto.setName(name);
        roomDto.setId(id);
        roomDto.setPrice(price);

        return roomDto;
    }

}
