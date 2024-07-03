package com.reservationSystem.SunTravel.service;

import com.reservationSystem.SunTravel.entity.Contract;
import com.reservationSystem.SunTravel.entity.Hotel;

import java.util.Date;
import java.util.List;

public interface ContractService
{
    public Contract addContract( Contract contract );

   public  List<Contract> fetchContractList();

    public Contract fetchContractById( Long contractId );

   public void deleteContractById( Long contractId );

    public Contract updateContract( Long contractId, Contract contract );

    public List<Contract> getContractsBasedOnCheckInAndNights( Date checkInDate, int numberOfNights);
}
