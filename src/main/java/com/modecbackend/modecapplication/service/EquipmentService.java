package com.modecbackend.modecapplication.service;

import com.modecbackend.modecapplication.model.Equipment;

import java.util.List;

public interface EquipmentService {
    Equipment createEquipment(Equipment equipment) throws Exception;

    Equipment updateEquipment(Equipment equipment);

    List<Equipment> getAllEquipment();

    Equipment getEquipmentById(long equipmentId);

    void deleteEquipment(long id);
}

