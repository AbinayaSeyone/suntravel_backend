package com.reservationSystem.SunTravel.repository;

import com.reservationSystem.SunTravel.entity.Contract;
import com.reservationSystem.SunTravel.entity.ContractedRoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractedRoomTypeRepository extends JpaRepository<ContractedRoomType,Long>
{
    //List<ContractedRoomType> findByContractIdIn( List<Long> contractIds );

    List<ContractedRoomType> findByContractIn( List<Contract> contracts );
}
