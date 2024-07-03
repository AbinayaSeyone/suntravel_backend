package com.reservationSystem.SunTravel.repository;

import com.reservationSystem.SunTravel.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract,Long>
{
    List<Contract> findByStartDateLessThanEqualAndEndDateGreaterThanEqual( Date checkInDate, Date checkOutDate );
}
