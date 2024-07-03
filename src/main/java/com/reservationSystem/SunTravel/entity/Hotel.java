package com.reservationSystem.SunTravel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Hotel
{

    @Id
    @SequenceGenerator(
            name="hotel_sequence",
            sequenceName = "hotel_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy =
                            GenerationType.SEQUENCE,
                            generator = "hotel_sequence"
    )
   // @Column(name ="hotelId")
    private Long hotelId;
    private String hotelName;
    private String hotelLocation;
    private String imageUrl;
    @JsonIgnore
    @OneToMany(
            mappedBy = "hotel",
            cascade = CascadeType.ALL
    )
    private List<Contract> contracts;
    @JsonIgnore
    @OneToMany(
            mappedBy = "hotel",
            cascade = CascadeType.ALL

//            orphanRemoval = true
    )
    private List<RoomType> roomTypes;
    /*@JsonIgnore
    @OneToMany(
            mappedBy = "hotel",
            cascade = CascadeType.ALL
    )
    private List<Room> rooms;*/
}



