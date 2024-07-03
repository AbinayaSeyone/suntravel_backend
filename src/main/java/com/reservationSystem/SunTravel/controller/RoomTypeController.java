package com.reservationSystem.SunTravel.controller;

import com.reservationSystem.SunTravel.entity.RoomType;
import com.reservationSystem.SunTravel.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RoomTypeController
{
    @Autowired
    private RoomTypeService roomTypeService;

    @PostMapping("/roomTypeAdd")
    public RoomType addRoomType( @RequestBody RoomType roomType ) {
        return roomTypeService.addRoomType(roomType);
    }
    @GetMapping("/roomTypes")
    public List<RoomType> fetchRoomTypeList(){
        return roomTypeService.fetchRoomTypeList();
    }

    @GetMapping("/roomType/{id}")
    public RoomType fetchRoomTypeById(@PathVariable("id") Long roomTypeId)
    {
        return roomTypeService.fetchRoomTypeById(roomTypeId);
    }

    @GetMapping("/roomTypesByHotel/{hotelId}")
    public List<RoomType> fetchRoomTypeByHotelId(@PathVariable("hotelId") Long hotelId)
    {
        return roomTypeService.fetchRoomTypeByHotelId(hotelId);
    }


//    @GetMapping("/availableRoomTypes")
//    public List<RoomType> findAvailableRoomTypesTesting(@RequestParam String roomTypeName,
//                                                    @RequestParam int noOfAdults,
//                                                    @RequestParam long noOfRooms) {
//        return roomTypeService.fetchAvailableRoomTypesByRoomTypeAndNoOfAdultsAndNoOfRooms(roomTypeName,noOfAdults,noOfRooms);
//    }
}
