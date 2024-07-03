package com.reservationSystem.SunTravel.service;

import com.reservationSystem.SunTravel.entity.Hotel;
import com.reservationSystem.SunTravel.entity.RoomType;
import com.reservationSystem.SunTravel.repository.HotelRepository;
import com.reservationSystem.SunTravel.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomTypeServiceImplementation implements RoomTypeService
{
    @Autowired
    private RoomTypeRepository roomTypeRepository;
    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public RoomType addRoomType( RoomType roomType )
    {
        return roomTypeRepository.save( roomType );
    }

    @Override
    public List<RoomType> fetchRoomTypeList()
    {
        return roomTypeRepository.findAll();
    }

    @Override
    public RoomType fetchRoomTypeById( Long roomTypeId )
    {
        return roomTypeRepository.findById( roomTypeId ).get();
    }

    @Override
    public List<RoomType> fetchRoomTypeByHotelId( Long hotelId ){
        Optional<Hotel> hotel= hotelRepository.findById( hotelId );
        return roomTypeRepository.findByHotel(hotel);
    }

//    @Override
//    public List<RoomType> fetchAvailableRoomTypesByRoomTypeAndNoOfAdultsAndNoOfRooms( String roomTypeName, int noOfAdults, long noOfRooms )
//    {
//        return roomTypeRepository
//                       .findByRoomTypeNameIgnoreCaseAndMaxAdultsGreaterThanEqualAndNoOfRoomsGreaterThanEqual
//                               ( roomTypeName, noOfAdults, noOfRooms );
//    }

}
