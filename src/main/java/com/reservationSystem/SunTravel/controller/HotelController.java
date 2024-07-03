package com.reservationSystem.SunTravel.controller;

import com.reservationSystem.SunTravel.entity.Hotel;
import com.reservationSystem.SunTravel.entity.RoomTypeCondition;
import com.reservationSystem.SunTravel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @PostMapping("/hotelAdd")
    public Hotel addHotel( @RequestBody Hotel hotel) {
        return hotelService.addHotel(hotel);
    }

    @GetMapping("/hotels")
    public List<Hotel> fetchHotelList(){
        return hotelService.fetchHotelList();
    }

    @GetMapping("/hotels/{id}")
    public Hotel fetchHotelById(@PathVariable("id") Long hotelId)
    {
        return hotelService.fetchHotelById(hotelId);
    }

    @DeleteMapping("/hotels/{id}")
    public String deleteHotelById(@PathVariable("id") Long hotelId){
        hotelService.deleteHotelById(hotelId);
        return "Hotel deleted successfully";
    }
    @PutMapping("/hotels/{id}")
    public Hotel updateHotel(@PathVariable("id") Long hotelId,@RequestBody Hotel hotel){

        return hotelService.updateHotel(hotelId,hotel);
    }
    @GetMapping("/hotels/name/{name}")
    public List<Hotel> fetchHotelByName(@PathVariable("name") String hotelName)
    {
        return hotelService.fetchHotelByName( hotelName );
    }

//    @PostMapping("/available-hotels")
//    public List<Hotel> findAvailableHotels(@RequestBody List<RoomTypeCondition> conditions) {
//        return hotelService.findAvailableHotels(conditions);
//    }

    @GetMapping(path = "hotel/image/{fileName}", produces = IMAGE_JPEG_VALUE)
    public byte[] getHotelImage(@PathVariable("fileName") String fileName) throws IOException
    {
        return Files.readAllBytes( Paths.get(System.getProperty("user.home") + "/Downloads/hotelimages/" + fileName));
    }





}

