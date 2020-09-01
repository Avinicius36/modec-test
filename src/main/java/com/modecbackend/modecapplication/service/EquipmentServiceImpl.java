package com.modecbackend.modecapplication.service;

import com.modecbackend.modecapplication.exception.ApiRequestException;
import com.modecbackend.modecapplication.model.Equipment;
import com.modecbackend.modecapplication.model.EquipmentStatus;
import com.modecbackend.modecapplication.repository.EquipmentRepository;
import com.modecbackend.modecapplication.repository.VesselRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private VesselRepository vesselRepository;

    @Override
    public Equipment createEquipment(Equipment equipment, Long id) throws Exception {
        vesselRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        Equipment existentEquipment = equipmentRepository.findByCode(equipment.getCode());

        if (existentEquipment != null && !existentEquipment.equals(equipment)) {
            throw new ApiRequestException("This code already exists in Database, please consider changing the code");
        }
        equipment.setStatus(EquipmentStatus.ACTIVE);
        equipment.setVesselId(id);

        return equipmentRepository.save(equipment);
    }

    @Override
    public Equipment updateEquipment(Equipment equipment) {
        List<Equipment> equipmentsToValidate = equipmentRepository.findAll();
        validateNames(equipmentsToValidate, equipment.getCode());

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
    public List<Equipment> getAllEquipment(final Long id) throws EntityNotFoundException {
        vesselRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        List<Equipment> equipmentList = equipmentRepository.findAll();

        List<Equipment> equipmentsInVessel = equipmentList.stream().filter(p -> p.getVesselId().equals(id)).collect(Collectors.toList());

        return equipmentsInVessel;
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
