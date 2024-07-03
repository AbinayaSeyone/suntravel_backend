package com.reservationSystem.SunTravel.service;

import com.reservationSystem.SunTravel.entity.Contract;
import com.reservationSystem.SunTravel.entity.ContractedRoomType;
import com.reservationSystem.SunTravel.entity.Hotel;
import com.reservationSystem.SunTravel.entity.RoomType;
import com.reservationSystem.SunTravel.entity.RoomTypeCondition;
import com.reservationSystem.SunTravel.repository.ContractRepository;
import com.reservationSystem.SunTravel.repository.ContractedRoomTypeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
class ContractedRoomTypeServiceImplementationTest
{

    @Mock
    private ContractedRoomTypeRepository contractedRoomTypeRepository;
    @Mock
    private ContractRepository contractRepository;
    @InjectMocks
    private ContractedRoomTypeServiceImplementation contractedRoomTypeServiceImplementation;



    @Test
    void canFetchContractedRoomTypeList()
    {
        ContractedRoomType contractedRoomType=new ContractedRoomType();
        List<ContractedRoomType> contractedRoomTypeList=new ArrayList<>();
        contractedRoomTypeList.add( contractedRoomType );
        when(contractedRoomTypeRepository.findAll()).thenReturn( contractedRoomTypeList );
        List<ContractedRoomType> savedContractedRoomTypeList=contractedRoomTypeServiceImplementation.fetchContractedRoomTypeList();
        assertEquals( contractedRoomTypeList,savedContractedRoomTypeList);

    }

    @Test

    void addContractedRoomType()
    {
        Contract newContract=new Contract();
        RoomType newRoomTpe=new RoomType();
        ContractedRoomType newContractedRoomType=new ContractedRoomType(2L,1400L,3,4,newContract,newRoomTpe);
        when(contractedRoomTypeRepository.save( newContractedRoomType )).thenReturn( newContractedRoomType );
        ContractedRoomType contractedRoomTypeFromService=contractedRoomTypeServiceImplementation.addContractedRoomType( newContractedRoomType );
        assertEquals( newContractedRoomType,contractedRoomTypeFromService );
    }

    @Test

    void saveContractedRoomTypes()
    {
        Contract newContract=new Contract();
        RoomType newRoomTpe=new RoomType();
        ContractedRoomType newContractedRoomType=new ContractedRoomType(2L,1400L,3,4,newContract,newRoomTpe);
        ContractedRoomType newContractedRoomTypeTwo=new ContractedRoomType(3L,1400L,3,4,newContract,newRoomTpe);
        List<ContractedRoomType> contractedRoomTypeList=new ArrayList<>();
        contractedRoomTypeList.addAll( Arrays.asList(newContractedRoomType, newContractedRoomTypeTwo));
        when(contractedRoomTypeRepository.saveAll( contractedRoomTypeList )).thenReturn( contractedRoomTypeList );
        List<ContractedRoomType> contractedRoomTypeListFromService=contractedRoomTypeServiceImplementation.saveContractedRoomTypes( contractedRoomTypeList );
        assertEquals( contractedRoomTypeList,contractedRoomTypeListFromService );
    }

    @Test

    void filterAndGroupContracts()
    {

        Date checkInDate = new Date(124, 1, 1);
        int numberOfNights = 3;
        LocalDate checkInLocalDate = checkInDate.toInstant().atZone( ZoneId.systemDefault()).toLocalDate();
        LocalDate checkOutLocalDate = checkInLocalDate.plusDays(numberOfNights);
        Date checkOutDate = Date.from(checkOutLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date startDate = new Date(123,1,1);
        Date endDate = new Date(124,11,30);

        RoomTypeCondition condition1 = new RoomTypeCondition("Single", 2, 1);
        RoomTypeCondition condition2 = new RoomTypeCondition("Double", 2, 2);
        List<RoomTypeCondition> conditions = new ArrayList<>();
        conditions.add(condition1);
        conditions.add(condition2);

        Hotel newHotel = new Hotel();
        List<ContractedRoomType> x = new ArrayList<>();
        RoomType roomType = new RoomType(13L,"single",newHotel);

        Contract contract1 = new Contract(1L, startDate, endDate, 15, newHotel, x);
        Contract contract2 = new Contract(2L, startDate, endDate, 15, newHotel, x);
        Contract contract3 = new Contract(3L, startDate, endDate, 15, newHotel, x);
        List<Contract> contracts = new ArrayList<>();
        contracts.add( contract1 );
        contracts.add( contract2 );
        contracts.add( contract3 );

        when(contractRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(checkInDate, checkOutDate)).thenReturn(contracts);

        Hotel newHotel1 = new Hotel();
        List<ContractedRoomType> y = new ArrayList<>();
        RoomType roomTypeTest1 = new RoomType(3L, "Single", newHotel1);
        RoomType roomTypeTest2 = new RoomType(4L, "Double", newHotel1);

        ContractedRoomType contractedRoomType1 = new ContractedRoomType(1L, 1400L, 3, 4, contract1, roomTypeTest1);
        ContractedRoomType contractedRoomType2 = new ContractedRoomType(2L, 1400L, 3, 4, contract1, roomTypeTest2);
        ContractedRoomType contractedRoomType3 = new ContractedRoomType(3L, 1400L, 3, 4, contract2, roomTypeTest1);
        List<ContractedRoomType> contractedRoomTypes = new ArrayList<>();
        contractedRoomTypes.add(contractedRoomType1);
        contractedRoomTypes.add(contractedRoomType2);
        contractedRoomTypes.add(contractedRoomType3);

        when(contractedRoomTypeRepository.findByContractIn(contracts)).thenReturn(contractedRoomTypes);

        Map<Contract, List<ContractedRoomType>> groupedByContract = contractedRoomTypes.stream()
                                                                                       .collect(Collectors.groupingBy(ContractedRoomType::getContract));

        assertNotNull(groupedByContract);
        assertEquals(2, groupedByContract.size());

        assertTrue(groupedByContract.containsKey(contract1));
        assertTrue(groupedByContract.containsKey(contract2));
        assertFalse(groupedByContract.containsKey(contract3));

        assertEquals(2, groupedByContract.get(contract1).size());
        assertEquals(1, groupedByContract.get(contract2).size());


        List<Contract> filteredContracts = new ArrayList<>(groupedByContract.keySet());
        List<Contract> satisfiedContracts = new ArrayList<>();

        for (Contract contract : filteredContracts) {
            if (meetsConditions(groupedByContract.get(contract), conditions)) {
                satisfiedContracts.add(contract);
            }
        }

        assertEquals(1, satisfiedContracts.size());
        assertTrue(satisfiedContracts.contains(contract1));
        assertFalse(satisfiedContracts.contains(contract2));

        List<Contract> result = contractedRoomTypeServiceImplementation.filterAndGroupContracts(checkInDate, numberOfNights, conditions);

        assertEquals(satisfiedContracts, result);
        assertNotNull(result);
        System.out.println( "result = " + result );
        System.out.println( "satisfiedContracts = " + satisfiedContracts );

    }
    private boolean meetsConditions(List<ContractedRoomType> contractedRoomTypes, List<RoomTypeCondition> conditions) {
        for (RoomTypeCondition condition : conditions) {
            boolean conditionSatisfied = contractedRoomTypes.stream()
                                                            .anyMatch(rt -> {
                                                                String roomTypeName = rt.getRoomType().getRoomTypeName();
                                                                return roomTypeName != null && roomTypeName.equals(condition.getRoomTypeName())
                                                                               && rt.getMaxAdults() >= condition.getNoOfAdults()
                                                                               && rt.getNoOfRooms() >= condition.getNoOfRooms();
                                                            });

            if (!conditionSatisfied) {
                return false; // If any condition is not satisfied, return false
            }
        }
        return true; // All conditions are satisfied
    }

}