package com.reservationSystem.SunTravel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomTypeCondition {
    private String roomTypeName;
    private int noOfAdults;
    private long noOfRooms;
}
