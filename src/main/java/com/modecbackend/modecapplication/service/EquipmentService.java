package com.modecbackend.modecapplication.service;

import com.modecbackend.modecapplication.model.Equipment;

import java.util.List;

public interface EquipmentService {
    Equipment createEquipment(Equipment equipment, Long id) throws Exception;

    Equipment updateEquipment(Equipment equipment, Long vesselId, Long id);

    List<Equipment> getAllEquipment(Long id);

    Equipment getEquipmentById(Long equipmentId);

    void deleteEquipment(Long id, Long vesselId);

}

