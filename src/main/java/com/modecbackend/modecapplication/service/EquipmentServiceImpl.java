package com.modecbackend.modecapplication.service;

import com.modecbackend.modecapplication.exception.ResourceNotFoundException;
import com.modecbackend.modecapplication.model.Equipment;
import com.modecbackend.modecapplication.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Override
    public Equipment createEquipment(Equipment equipment) {
       return equipmentRepository.save(equipment);
    }

    @Override
    public Equipment updateEquipment(Equipment equipment) {
        Optional<Equipment> equipmentDb = this.equipmentRepository.findById(equipment.getId());

        if (equipmentDb.isPresent()) {
            Equipment equipmentUpdate = equipmentDb.get();
            equipmentUpdate.setId(equipment.getId());
            equipmentUpdate.setName(equipment.getName());
            equipmentUpdate.setCode(equipment.getCode());
            equipment.setLocation(equipment.getLocation());

            equipmentRepository.save(equipmentUpdate);

            return equipmentUpdate;
        } else {
            throw new ResourceNotFoundException("Equipment not found with id: " + equipment.getId());
        }
    }

    @Override
    public List<Equipment> getAllEquipment() {
        return this.equipmentRepository.findAll();
    }

    @Override
    public Equipment getEquipmentById(long equipmentId) {
        Optional<Equipment> equipmentDb = this.equipmentRepository.findById(equipmentId);

        if (equipmentDb.isPresent()) {
            return equipmentDb.get();
        } else {
            throw new ResourceNotFoundException("Equipment not found with id: " + equipmentId);
        }
    }

    @Override
    public void deleteEquipment(long equipmentId) {
        Optional<Equipment> equipmentDb = this.equipmentRepository.findById(equipmentId);

        if (equipmentDb.isPresent()) {
            this.equipmentRepository.delete(equipmentDb.get());
        } else {
            throw new ResourceNotFoundException("Equipment not found with id: " + equipmentId);
        }
    }
}
