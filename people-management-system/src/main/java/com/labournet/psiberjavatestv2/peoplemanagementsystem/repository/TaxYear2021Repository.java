package com.labournet.psiberjavatestv2.peoplemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labournet.psiberjavatestv2.peoplemanagementsystem.entity.TaxYear2021Entity;

@Repository
public interface TaxYear2021Repository extends JpaRepository<TaxYear2021Entity, Long> {

}
