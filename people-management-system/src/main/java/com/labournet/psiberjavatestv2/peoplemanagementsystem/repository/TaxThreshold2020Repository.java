package com.labournet.psiberjavatestv2.peoplemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labournet.psiberjavatestv2.peoplemanagementsystem.entity.TaxThreshold2020Entity;

@Repository
public interface TaxThreshold2020Repository extends JpaRepository<TaxThreshold2020Entity, Long> {

}
