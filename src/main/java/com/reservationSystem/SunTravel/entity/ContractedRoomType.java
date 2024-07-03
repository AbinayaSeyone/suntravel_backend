package com.reservationSystem.SunTravel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//public class ContractedRoomType
//{
//    @Id
//    @SequenceGenerator(
//            name="contractedRoomType_sequence",
//            sequenceName = "contractRoomType_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(strategy =
//                            GenerationType.SEQUENCE,
//            generator = "contractRoomType_sequence"
//    )
//    private Long contractedRoomTypeId;
//    private Long price;
//    private int maxAdults;
//    private int noOfRooms;
//    @ManyToOne
//    @JoinColumn(
//            name = "contractId"
//    )
//    private Contract contract;
//    @ManyToOne
//    @JoinColumn(
//            name ="roomTypeId"
//    )
//    private RoomType roomType;
//}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ContractedRoomType
{

    @Id
    @SequenceGenerator(
            name = "contractedRoomType_sequence",
            sequenceName = "contractedRoomType_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contractedRoomType_sequence")
    private Long contractedRoomTypeId;

    private Long price;
    private int maxAdults;
    private int noOfRooms;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "contractId")
    private Contract contract;

    @ManyToOne
    @JoinColumn(name = "roomTypeId")
    private RoomType roomType;

    // other getters and setters
}
