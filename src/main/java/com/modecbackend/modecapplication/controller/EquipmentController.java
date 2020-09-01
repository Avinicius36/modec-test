package com.modecbackend.modecapplication.controller;

import com.modecbackend.modecapplication.model.Equipment;
import com.modecbackend.modecapplication.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/equipments/")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping
    public ResponseEntity<List<Equipment>> getAllEquipment() {
        return ResponseEntity.ok().body(equipmentService.getAllEquipment());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable Long id) {
        return ResponseEntity.ok().body(equipmentService.getEquipmentById(id));
    }

    @PostMapping
    public ResponseEntity<Equipment> createEquipment(@RequestBody Equipment equipment) {
        return ResponseEntity.ok().body(this.equipmentService.createEquipment(equipment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipment> updateEquipment(@PathVariable long id, @RequestBody Equipment equipment) {
        equipment.setId(id);
        return ResponseEntity.ok().body(this.equipmentService.updateEquipment(equipment));
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteEquipment(@PathVariable long id) {
        this.equipmentService.deleteEquipment(id);
        return HttpStatus.OK;
    }

}
