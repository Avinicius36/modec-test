package com.modecbackend.modecapplication.repository;

import com.modecbackend.modecapplication.model.Vessel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VesselRepository extends JpaRepository<Vessel, Long> {
    Vessel findByCode(String code);
}
