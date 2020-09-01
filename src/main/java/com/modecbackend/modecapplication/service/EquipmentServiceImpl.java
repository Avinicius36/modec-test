package com.modecbackend.modecapplication.service;

import com.modecbackend.modecapplication.exception.ApiRequestException;
import com.modecbackend.modecapplication.model.Equipment;
import com.modecbackend.modecapplication.model.EquipmentStatus;
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
    public Equipment createEquipment(Equipment equipment) throws Exception {
        Equipment existentEquipment = equipmentRepository.findByCode(equipment.getCode());

        if (existentEquipment != null && !existentEquipment.equals(equipment)) {
            throw new ApiRequestException("This code already exists in Database, please consider changing the code");
        }
        equipment.setStatus(EquipmentStatus.ACTIVE);
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

            List<Equipment> equipmentsToValidate = equipmentRepository.findAll();

            validateNames(equipmentsToValidate, equipmentUpdate.getCode());
            equipmentRepository.save(equipmentUpdate);

            return equipmentUpdate;
        } else {
            throw new ApiRequestException("Equipment not found with id: " + equipment.getId());
        }
    }

    private boolean validateNames(final List<Equipment> list, final String code) {
        Boolean checkingIfNameIsPresent = list.stream().filter(o -> o.getCode().equals(code)).findFirst().isPresent();
        if(checkingIfNameIsPresent == true) {
            throw new ApiRequestException("This code already exists in Database, please consider changing the code");
        } else {
            return false;
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
            throw new ApiRequestException("Equipment not found with id: " + equipmentId);
        }
    }

    @Override
    public void deleteEquipment(long equipmentId) {
        Optional<Equipment> equipmentDb = this.equipmentRepository.findById(equipmentId);

        if (equipmentDb.isPresent()) {
            this.equipmentRepository.delete(equipmentDb.get());
        } else {
            throw new ApiRequestException("Equipment not found with id: " + equipmentId);
        }
    }
}
