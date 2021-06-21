package com.labournet.psiberjavatestv2.peoplemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labournet.psiberjavatestv2.peoplemanagementsystem.entity.TaxRebate2021Entity;

@Repository
public interface TaxRebate2021Repository extends JpaRepository<TaxRebate2021Entity, Long> {

}
