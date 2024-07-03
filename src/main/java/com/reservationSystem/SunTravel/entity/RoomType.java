package com.reservationSystem.SunTravel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RoomType
{
    @Id
    @SequenceGenerator(
            name="roomtype_sequence",
            sequenceName = "roomtype_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "roomtype_sequence"
    )
    private Long roomTypeId;
    private String roomTypeName;

    @ManyToOne(
            optional = false
    )
    @JoinColumn(
            name="hotelId"

            //referencedColumnName = "hotelId"
    )
    private Hotel hotel;
//    @JsonIgnore
//    @OneToMany(
//            mappedBy = "roomType",
//            cascade = CascadeType.ALL
//    )
//    List<ContractedRoomType> contractedRoomTypeList;

}
