package com.modecbackend.modecapplication.controller;

import com.modecbackend.modecapplication.model.Equipment;
import com.modecbackend.modecapplication.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/equipments/")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping("/{vessel-id}")
    public List<Equipment> getAllEquipment(@NonNull @PathVariable (value = "vessel-id") final Long vesselId) {
        return equipmentService.getAllEquipment(vesselId);
    }

    @GetMapping("/{vessel-id}/{id}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable Long id) {
        return ResponseEntity.ok().body(equipmentService.getEquipmentById(id));
    }

    @PostMapping("/{vessel-id}")
    public Equipment createEquipment(@NonNull @RequestBody Equipment equipment, @NonNull @PathVariable(value = "vessel-id") final Long vesselId) throws Exception {
        return this.equipmentService.createEquipment(equipment, vesselId);
    }

    @PatchMapping("/{vessel-id}/{id}")
    public Equipment updateEquipmentPartially(@NonNull @RequestBody final Equipment equipment,
    @NonNull @PathVariable(value = "vessel-id") final Long vesselId,
    @NonNull @PathVariable(value = "id") final Long id) throws Exception {

        return this.equipmentService.updateEquipment(equipment, vesselId, id);
    }

    @DeleteMapping("/{vessel-id}/{id}")
    public void deleteEquipment(
        @NonNull @PathVariable(value = "vessel-id") final Long vesselId,
        @NonNull @PathVariable(value = "id") final Long id
    ) throws Exception
    {
        this.equipmentService.deleteEquipment(vesselId, id);
    }

}
