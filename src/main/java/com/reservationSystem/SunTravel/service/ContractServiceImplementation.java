package com.reservationSystem.SunTravel.service;

import com.reservationSystem.SunTravel.entity.Contract;
import com.reservationSystem.SunTravel.entity.ContractedRoomType;
import com.reservationSystem.SunTravel.entity.Hotel;
import com.reservationSystem.SunTravel.repository.ContractRepository;
import com.reservationSystem.SunTravel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ContractServiceImplementation implements ContractService
{
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private ContractedRoomTypeService contractedRoomTypeService;
    @Override
//    public Contract addContract( Contract contract )
//    {
//        return contractRepository.save( contract );
//    }
        public Contract addContract(Contract contract) {
            // Save the Contract to generate an ID
            Contract savedContract = contractRepository.save(contract);

            // Get the generated ID
            Long contractId = savedContract.getContractId();

            // Set the Contract ID for each ContractedRoomType
            List<ContractedRoomType> contractedRoomTypes = contract.getContractedRoomTypeList();
            if (contractedRoomTypes != null) {
                contractedRoomTypes.forEach(contractedRoomType -> contractedRoomType.setContract(savedContract));
            }

            // Save the associated ContractedRoomType instances
            contractedRoomTypeService.saveContractedRoomTypes(contractedRoomTypes);

            // Optionally, you may want to update the ContractedRoomTypeList in the Contract
            // savedContract.setContractedRoomTypeList(contractedRoomTypes);

            return savedContract;
        }

        // Additional methods if needed



    @Override
    public List<Contract> fetchContractList()
    {
        return contractRepository.findAll();
    }

    @Override
    public Contract fetchContractById( Long contractId )
    {
        return contractRepository.findById( contractId ).get();
    }

    @Override
    public void deleteContractById( Long contractId )
    {
        contractRepository.deleteById( contractId );
    }

    @Override
    public Contract updateContract( Long contractId, Contract contract )
    {
        Contract existingContract=contractRepository.findById( contractId ).get();
        if( Objects.nonNull( contract.getStartDate() )){
            existingContract.setStartDate( contract.getStartDate() );
        }
        if( Objects.nonNull( contract.getEndDate() )){
            existingContract.setEndDate( contract.getStartDate() );
        }
        if( Objects.nonNull( contract.getHotel().getHotelId() )){
            existingContract.setHotel( hotelRepository.findById( contract.getHotel().getHotelId() ).get() );
        }
        return contractRepository.save( existingContract );
    }
    @Override
        public List<Contract> getContractsBasedOnCheckInAndNights( Date checkInDate, int numberOfNights) {
            // Calculate check-out date based on check-in date and number of nights
            LocalDate checkInLocalDate = checkInDate.toInstant().atZone( ZoneId.systemDefault()).toLocalDate();
            LocalDate checkOutLocalDate = checkInLocalDate.plusDays(numberOfNights);
            Date checkOutDate = Date.from(checkOutLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            // Query contracts based on calculated check-in and check-out dates
            return contractRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(
                    checkOutDate, checkInDate);
        }



}
