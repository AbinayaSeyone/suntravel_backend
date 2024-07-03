package com.reservationSystem.SunTravel.service;

import com.reservationSystem.SunTravel.entity.Contract;
import com.reservationSystem.SunTravel.entity.ContractedRoomType;
import com.reservationSystem.SunTravel.entity.RoomTypeCondition;
import com.reservationSystem.SunTravel.repository.ContractRepository;
import com.reservationSystem.SunTravel.repository.ContractedRoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ContractedRoomTypeServiceImplementation implements ContractedRoomTypeService
{
    @Autowired
    private ContractedRoomTypeRepository contractedRoomTypeRepository;
    @Autowired
    private ContractRepository contractRepository;
    @Override
    public List<ContractedRoomType> fetchContractedRoomTypeList()
    {
        return contractedRoomTypeRepository.findAll();
    }

    @Override
    public ContractedRoomType addContractedRoomType( ContractedRoomType contractedRoomType )
    {
        return contractedRoomTypeRepository.save( contractedRoomType );
    }
    @Override
    public List<ContractedRoomType> saveContractedRoomTypes(List<ContractedRoomType> contractedRoomTypes) {
        return contractedRoomTypeRepository.saveAll(contractedRoomTypes);
    }


    @Override
    public List<Contract> filterAndGroupContracts(Date checkInDate, int numberOfNights, List<RoomTypeCondition> conditions) {
        // Step 1: Fetch contracts based on checkInDate and numberOfNights

            LocalDate checkInLocalDate = checkInDate.toInstant().atZone( ZoneId.systemDefault()).toLocalDate();
            LocalDate checkOutLocalDate = checkInLocalDate.plusDays(numberOfNights);
            Date checkOutDate = Date.from(checkOutLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Contract> contracts=contractRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(
                    checkInDate, checkOutDate);

        // Step 2: Filter ContractedRoomType records by contracts
        List<ContractedRoomType> contractedRoomTypes = contractedRoomTypeRepository.findByContractIn(contracts);

        // Step 3: Group filtered records by contract
        Map<Contract, List<ContractedRoomType>> groupedByContract = contractedRoomTypes.stream()
                                                                                       .collect(Collectors.groupingBy(ContractedRoomType::getContract));

        // Step 4: Check conditions for each group and return contracts that satisfy all conditions
        return groupedByContract.entrySet().stream()
                                .filter(entry -> meetsConditions(entry.getValue(), conditions))
                                .map(Map.Entry::getKey)
                                .collect(Collectors.toList());
    }

    private boolean meetsConditions(List<ContractedRoomType> contractedRoomTypes, List<RoomTypeCondition> conditions) {
        for (RoomTypeCondition condition : conditions) {
            boolean conditionSatisfied = contractedRoomTypes.stream()
                                                            .anyMatch(rt -> rt.getRoomType().getRoomTypeName().equals(condition.getRoomTypeName())
                                                                                    && rt.getMaxAdults() >= condition.getNoOfAdults()
                                                                                    && rt.getNoOfRooms() >= condition.getNoOfRooms());

            if (!conditionSatisfied) {
                return false; // If any condition is not satisfied, return false
            }
        }
        return true; // All conditions are satisfied
    }


}
