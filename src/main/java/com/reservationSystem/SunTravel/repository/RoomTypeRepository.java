package com.reservationSystem.SunTravel.repository;

import com.reservationSystem.SunTravel.entity.Hotel;
import com.reservationSystem.SunTravel.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
    List<RoomType> findByHotel( Optional<Hotel> hotel );
//    List<RoomType> findByRoomTypeNameIgnoreCaseAndMaxAdultsGreaterThanEqualAndNoOfRoomsGreaterThanEqual(
//            String roomTypeName,
//            int noOfAdults,
//            long noOfRooms
//    );
}
