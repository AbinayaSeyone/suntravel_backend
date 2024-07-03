package com.reservationSystem.SunTravel.service;

import com.reservationSystem.SunTravel.entity.Hotel;
import com.reservationSystem.SunTravel.entity.RoomTypeCondition;
import com.reservationSystem.SunTravel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class HotelServiceImplementation implements HotelService
{
    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel addHotel( Hotel hotel )
    {
        hotel.setImageUrl(setHotelImageUrl());
        return hotelRepository.save( hotel );
    }

    @Override
    public List<Hotel> fetchHotelList()
    {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel fetchHotelById( Long hotelId )
    {
        return hotelRepository.findById( hotelId ).get();
    }

    @Override
    public void deleteHotelById( Long hotelId )
    {
        hotelRepository.deleteById( hotelId );
    }

    @Override
    public Hotel updateHotel( Long hotelId, Hotel hotel )
    {
        Hotel existingHotel = hotelRepository.findById( hotelId ).get();
        if( Objects.nonNull( hotel.getHotelName() ) && !"".equalsIgnoreCase( hotel.getHotelName() ) )
        {
            existingHotel.setHotelName( hotel.getHotelName() );
        }
        if( Objects.nonNull( hotel.getHotelLocation() ) && !"".equalsIgnoreCase( hotel.getHotelLocation() ) )
        {
            existingHotel.setHotelLocation( hotel.getHotelLocation() );
        }
        

        return hotelRepository.save( existingHotel );
    }

    @Override
    public List<Hotel> fetchHotelByName(String hotelName )
    {
        return hotelRepository.findByHotelNameIgnoreCase(hotelName);
    }
//    @Override
//    public List<Hotel> fetchAvailableHotelsByConditions(String roomTypeName, int noOfAdults, long noOfRooms) {
//            return hotelRepository.findAll().stream()
//                                  .filter(hotel -> hasAllRoomConditions(hotel, roomTypeName, noOfAdults, noOfRooms))
//                                  .collect( Collectors.toList());
//    }
//
//        boolean hasAllRoomConditions(Hotel hotel, String roomTypeName, int noOfAdults, long noOfRooms) {
//            return hotel.getRoomTypes().stream()
//                        .allMatch(roomType ->
//                                          roomType.getRoomTypeName().equalsIgnoreCase(roomTypeName)&&
//                                                  roomType.getMaxAdults() >= noOfAdults &&
//                                                  roomType.getNoOfRooms() >= noOfRooms
//                        );
//        }
//

    private String setHotelImageUrl() {
        String[] imageNames = { "hotel1.jpg", "hotel2.jpg", "hotel3.jpg" };
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/hotel/image/" + imageNames[new Random().nextInt(3)]).toUriString();
        //return "hi";
    }

//     @Override
//
//        public List<Hotel> findAvailableHotels(List<RoomTypeCondition> conditions) {
//            List<Hotel> allHotels = hotelRepository.findAll(); // Fetch all hotels
//
//            // Filter hotels based on conditions
//
//         return allHotels.stream()
//                         .filter(hotel -> meetsConditions(hotel, conditions))
//                         .collect(Collectors.toList());
//        }
//
//        private boolean meetsConditions(Hotel hotel, List<RoomTypeCondition> conditions) {
//            for (RoomTypeCondition condition : conditions) {
//                boolean conditionSatisfied = hotel.getRoomTypes().stream()
//                                                  .anyMatch(rt -> rt.getRoomTypeName().equals(condition.getRoomTypeName())
//                                                                          && rt.getMaxAdults() >= condition.getNoOfAdults()
//                                                                          && rt.getNoOfRooms() >= condition.getNoOfRooms());
//
//                if (!conditionSatisfied) {
//                    return false; // If any condition is not satisfied, return false
//                }
//            }
//            return true; // All conditions are satisfied
//        }


}
