package com.reservationSystem.SunTravel.service;

import com.reservationSystem.SunTravel.entity.Contract;
import com.reservationSystem.SunTravel.entity.ContractedRoomType;
import com.reservationSystem.SunTravel.entity.RoomTypeCondition;

import java.util.Date;
import java.util.List;

public interface ContractedRoomTypeService
{
    public List<ContractedRoomType> fetchContractedRoomTypeList();

   public ContractedRoomType addContractedRoomType( ContractedRoomType contractedRoomType );

    public List<ContractedRoomType> saveContractedRoomTypes(List<ContractedRoomType> contractedRoomTypes);

    public List<Contract> filterAndGroupContracts(Date checkInDate, int numberOfNights, List<RoomTypeCondition> conditions);
}
