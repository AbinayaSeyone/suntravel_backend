package com.reservationSystem.SunTravel.service;

import com.reservationSystem.SunTravel.entity.Hotel;
import com.reservationSystem.SunTravel.entity.RoomTypeCondition;

import java.util.List;

public interface HotelService
{
   public Hotel addHotel( Hotel hotel );

   public List<Hotel> fetchHotelList();

   public  Hotel fetchHotelById( Long hotelId );

   public  void deleteHotelById( Long hotelId );

   public Hotel updateHotel( Long hotelId, Hotel hotel );

  public List<Hotel> fetchHotelByName( String hotelName );

//   public List<Hotel> fetchAvailableHotelsByConditions(String roomTypeName, int noOfAdults, long noOfRooms);
//   public List<Hotel> findAvailableHotels(List<RoomTypeCondition> conditions);
}
