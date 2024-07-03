package com.reservationSystem.SunTravel.controller;

import com.reservationSystem.SunTravel.entity.Contract;
import com.reservationSystem.SunTravel.entity.ContractedRoomType;
import com.reservationSystem.SunTravel.entity.RoomTypeCondition;
import com.reservationSystem.SunTravel.service.ContractedRoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
@RestController
@CrossOrigin(origins = "*")
public class ContractedRoomTypeController
{
    @Autowired
    private ContractedRoomTypeService contractedRoomTypeService;

    @PostMapping("/contractedRoomTypesAdd")
    public ContractedRoomType addContractedRoomType( @RequestBody ContractedRoomType contractedRoomType ) {
        return contractedRoomTypeService.addContractedRoomType(contractedRoomType);
    }

    @GetMapping("/contractedRoomTypes")
    public List<ContractedRoomType> fetchContractedRoomTypeList(){
        return contractedRoomTypeService.fetchContractedRoomTypeList();
    }

    @PostMapping("/contractedRoomTypes/filterAndGroup")
    public List<Contract> filterAndGroupContractedRoomTypes(
            @RequestParam("checkInDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkInDate,
            @RequestParam("numberOfNights") int numberOfNights,
            @RequestBody List<RoomTypeCondition> conditions) {

        return contractedRoomTypeService.filterAndGroupContracts( checkInDate,  numberOfNights,  conditions);
    }


}
