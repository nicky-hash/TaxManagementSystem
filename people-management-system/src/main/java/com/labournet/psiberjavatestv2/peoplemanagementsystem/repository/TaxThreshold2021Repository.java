package com.labournet.psiberjavatestv2.peoplemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labournet.psiberjavatestv2.peoplemanagementsystem.entity.TaxThreshold2021Entity;

@Repository
public interface TaxThreshold2021Repository extends JpaRepository<TaxThreshold2021Entity, Long> {

}
