package com.HotelProjectcode.HotelManagement.services.customer.booking;

import com.HotelProjectcode.HotelManagement.dto.ReservationDto;
import com.HotelProjectcode.HotelManagement.dto.ReservationResponseDto;
import com.HotelProjectcode.HotelManagement.entity.Reservation;
import com.HotelProjectcode.HotelManagement.entity.Room;
import com.HotelProjectcode.HotelManagement.entity.User;
import com.HotelProjectcode.HotelManagement.enums.ReservationStatus;
import com.HotelProjectcode.HotelManagement.repository.ReservationRepository;
import com.HotelProjectcode.HotelManagement.repository.RoomRepository;
import com.HotelProjectcode.HotelManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService{

    private final UserRepository userRepository;

    private final RoomRepository roomRepository;

    private final ReservationRepository reservationRepository;

    private static final int SEARCH_ROWS_PER_PAGE = 4;

    public boolean postReservation(ReservationDto reservationDto){
        Optional<User> optionalUser = userRepository.findById(reservationDto.getUserId());
        Optional<Room> optionalRoom = roomRepository.findById(reservationDto.getRoomId());

        if(optionalRoom.isPresent() && optionalUser.isPresent()){
            Reservation reservation = new Reservation();

            reservation.setRoom(optionalRoom.get());
            reservation.setUser(optionalUser.get());
            reservation.setCheckInDate(reservationDto.getCheckInDate());
            reservation.setCheckOutDate(reservationDto.getCheckOutDate());
            reservation.setReservationStatus(ReservationStatus.PENDING);

            Long days = ChronoUnit.DAYS.between(reservationDto.getCheckInDate(),reservationDto.getCheckOutDate());
            reservation.setPrice(optionalRoom.get().getPrice() * days);

            reservationRepository.save(reservation);

            return true;
        }else{
            return false;
        }
    }

    public ReservationResponseDto getAllReservationByUserId(Long userId,int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber,SEARCH_ROWS_PER_PAGE);

        Page<Reservation> reservationPage = reservationRepository.findAllByUserId(pageable,userId);

        ReservationResponseDto reservationResponseDto = new ReservationResponseDto();

        reservationResponseDto.setReservationDtoList(reservationPage.stream().map(Reservation::getReservationDto)
                .collect(Collectors.toList()));

        reservationResponseDto.setPageNumber(reservationPage.getPageable().getPageNumber());
        reservationResponseDto.setTotalPages(reservationPage.getTotalPages());

        return reservationResponseDto;
    }

}
