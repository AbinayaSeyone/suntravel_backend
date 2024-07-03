package com.reservationSystem.SunTravel.service;

import com.reservationSystem.SunTravel.entity.Contract;
import com.reservationSystem.SunTravel.entity.ContractedRoomType;
import com.reservationSystem.SunTravel.repository.ContractRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith( MockitoExtension.class )
class ContractServiceImplementationTest
{
    @InjectMocks
    private ContractServiceImplementation contractServiceImplementation;
    @Mock

    private ContractedRoomTypeServiceImplementation contractedRoomTypeServiceImplementation;

    @Mock
    private ContractRepository contractRepository;

    @Test
    void addContract()
    {
        // Mock Contract and associated ContractedRoomTypeList
        Contract contract = new Contract();
        List<ContractedRoomType> contractedRoomTypes = Collections.singletonList(new ContractedRoomType());
        contract.setContractedRoomTypeList(contractedRoomTypes);

        // Mock ContractRepository's save method to return savedContract
        Contract savedContract =  mock(Contract.class);
        savedContract.setContractId(1L);
        when(contractRepository.save(contract)).thenReturn(savedContract);

        // Mock ContractedRoomTypeService's saveContractedRoomTypes method
       when(contractedRoomTypeServiceImplementation.saveContractedRoomTypes(contractedRoomTypes)).thenReturn( contractedRoomTypes );

        // Call the method to be tested
        Contract result = contractServiceImplementation.addContract(contract);

        // Verify that ContractRepository's save method was called with the contract object
        verify(contractRepository, times(1)).save(contract);

        // Verify that ContractedRoomTypeService's saveContractedRoomTypes method was called with the contractedRoomTypes list
        verify(contractedRoomTypeServiceImplementation, times(1)).saveContractedRoomTypes(contractedRoomTypes);

        // Verify that the returned contract object has the correct ID
        assert result != null;
        assert result.getContractId() != null;
        //assert result.getContractId().longValue() == 1L;

        assertEquals( result,savedContract );


    }

    @Test
    @Disabled
    void fetchContractList()
    {
    }

    @Test
    @Disabled
    void fetchContractById()
    {
    }
}