package com.modecbackend.modecapplication.repository;

import com.modecbackend.modecapplication.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}
