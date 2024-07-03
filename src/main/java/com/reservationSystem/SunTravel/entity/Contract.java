package com.reservationSystem.SunTravel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//
//public class Contract
//{
//
//    @Id
//    @SequenceGenerator(
//            name="contract_sequence",
//            sequenceName = "contract_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(strategy =
//                            GenerationType.SEQUENCE,
//                            generator = "contract_sequence"
//    )
//    private Long contractId;
//    private Date startDate;
//    private Date endDate;
//    @ManyToOne
//    @JoinColumn(
//            name = "hotelId"
//    )
//    private Hotel hotel;
//
//    @OneToMany(
//            mappedBy = "contract",
//            cascade = CascadeType.ALL
//    )
//    List<ContractedRoomType> contractedRoomTypeList;
//}
//
//
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Contract {

    @Id
    @SequenceGenerator(
            name = "contract_sequence",
            sequenceName = "contract_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_sequence")
    private Long contractId;
    private Date startDate;
    private Date endDate;
    private Integer markUpRate;

    @ManyToOne
    @JoinColumn(name = "hotelId")
    private Hotel hotel;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    List<ContractedRoomType> contractedRoomTypeList ;



    // other getters and setters
}
