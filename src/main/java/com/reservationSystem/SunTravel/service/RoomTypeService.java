package com.reservationSystem.SunTravel.service;

import com.reservationSystem.SunTravel.entity.Contract;
import com.reservationSystem.SunTravel.entity.RoomType;

import java.util.List;

public interface RoomTypeService
{

    public RoomType addRoomType( RoomType roomType );

    public List<RoomType> fetchRoomTypeList();

    public RoomType fetchRoomTypeById( Long roomTypeId );

    public List<RoomType> fetchRoomTypeByHotelId( Long hotelId );


    //public List<RoomType> fetchAvailableRoomTypesByRoomTypeAndNoOfAdultsAndNoOfRooms( String roomTypeName, int noOfAdults, long noOfRooms );
}
