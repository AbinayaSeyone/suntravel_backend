package com.reservationSystem.SunTravel.service;

import com.reservationSystem.SunTravel.entity.Contract;
import com.reservationSystem.SunTravel.entity.ContractedRoomType;
import com.reservationSystem.SunTravel.entity.Hotel;
import com.reservationSystem.SunTravel.entity.RoomType;
import com.reservationSystem.SunTravel.repository.HotelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
class HotelServiceImplementationTest
{
    @InjectMocks
    private HotelServiceImplementation hotelServiceImplementation;
    @Mock
    private HotelRepository hotelRepository;

    @Test
    void addHotel()
    {
        List<Contract> contractList=new ArrayList<>();
        List<RoomType> roomTypeList=new ArrayList<>();

        Hotel newHotel=new Hotel();
        newHotel.setHotelId( 1L );
        newHotel.setHotelName( "Jetwing" );
        newHotel.setHotelLocation( "Jaffna" );
        newHotel.setContracts( contractList );
        newHotel.setRoomTypes( roomTypeList );

        when(hotelRepository.save( newHotel )).thenReturn(new Hotel());
        Hotel savedHotel=hotelServiceImplementation.addHotel( newHotel );
        assertEquals( savedHotel,new Hotel() );
    }

    @Test
    void fetchHotelList()
    {
        Hotel newHotel=new Hotel();
        List<Hotel> hotelList=new ArrayList<>();
        hotelList.add( newHotel );
        when(hotelRepository.findAll()).thenReturn( hotelList );
        List<Hotel> hotelListFromService=hotelServiceImplementation.fetchHotelList();
        assertEquals( hotelList,hotelListFromService);
    }

    @Test
    void fetchHotelById()
    {
        Hotel newHotel=new Hotel();
        when(hotelRepository.findById( newHotel.getHotelId() )).thenReturn( Optional.of( newHotel ) );
        Hotel hotelFromService=hotelServiceImplementation.fetchHotelById( newHotel.getHotelId() );
        assertEquals( newHotel,hotelFromService );
    }

    @Test
    void deleteHotelById()
    {
        // Given
        Long hotelId = 1L;

        // When
        hotelServiceImplementation.deleteHotelById(hotelId);

        // Then
        verify(hotelRepository).deleteById(hotelId);
    }

    @Test
    void updateHotel()
    {
        List<Contract> contractList=new ArrayList<>();
        List<RoomType> roomTypeList=new ArrayList<>();
        Hotel existingHotel= new Hotel();
        existingHotel.setHotelId( 1L );
        existingHotel.setHotelName( "Jetwing" );
        existingHotel.setHotelLocation( "Jaffna" );
        existingHotel.setContracts( contractList );
        existingHotel.setRoomTypes( roomTypeList );
        when(hotelRepository.findById(existingHotel.getHotelId())).thenReturn(Optional.of(existingHotel));
        Hotel updatedHotel = new Hotel();
        updatedHotel.setHotelId( 1L );
        updatedHotel.setHotelName( "Jetwing" );
        updatedHotel.setHotelLocation( "Colomo" );
        updatedHotel.setContracts( contractList );
        updatedHotel.setRoomTypes( roomTypeList );
        when(hotelRepository.save(existingHotel)).thenReturn(updatedHotel);
        Hotel hotelFromService=hotelServiceImplementation.updateHotel( existingHotel.getHotelId(),existingHotel );
        assertEquals(updatedHotel, hotelFromService);

    }

    @Test
    void fetchHotelByName()
    {
        Hotel newHotel=new Hotel();
        List<Hotel> hotelList=new ArrayList<>();
        hotelList.add( newHotel );
        when(hotelRepository.findByHotelNameIgnoreCase( newHotel.getHotelName() )).thenReturn( hotelList );
        List <Hotel> hotelListFromService=hotelServiceImplementation.fetchHotelByName( newHotel.getHotelName() );
        assertEquals( hotelList,hotelListFromService );
    }
}