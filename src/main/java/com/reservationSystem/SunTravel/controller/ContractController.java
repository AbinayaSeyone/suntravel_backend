package com.reservationSystem.SunTravel.controller;

import com.reservationSystem.SunTravel.entity.Contract;
import com.reservationSystem.SunTravel.entity.Hotel;
import com.reservationSystem.SunTravel.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ContractController
{
    @Autowired
    private ContractService contractService;

    @PostMapping("/contractsAdd")
    public Contract addContract( @RequestBody Contract contract ) {
        return contractService.addContract(contract);
    }

    @GetMapping("/contracts")
    public List<Contract> fetchContractList(){
        return contractService.fetchContractList();
    }
    @GetMapping("/contracts/{id}")
    public Contract fetchContractById(@PathVariable("id") Long contractId)
    {
        return contractService.fetchContractById(contractId);
    }
    @DeleteMapping("/contracts/{id}")
    public String deleteContractById(@PathVariable("id") Long contractId){
        contractService.deleteContractById(contractId);
        return "Hotel deleted successfully";
    }
    @PutMapping("/contracts/{id}")
    public Contract updateContract(@PathVariable("id") Long contractId,@RequestBody Contract contract){

        return contractService.updateContract(contractId,contract);
    }
    @GetMapping("/contracts/checkDate")
    public List<Contract> getContractsBasedOnCheckInAndNights(
            @RequestParam("checkInDate") @DateTimeFormat(pattern = "yyyy-MM-dd")Date checkInDate,
            @RequestParam("numberOfNights") int numberOfNights) {

        return contractService.getContractsBasedOnCheckInAndNights(checkInDate, numberOfNights);
    }


}
