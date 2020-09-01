package com.modecbackend.modecapplication.controller;

import com.modecbackend.modecapplication.model.Vessel;
import com.modecbackend.modecapplication.service.VesselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vessels/")
public class VesselController {

    @Autowired
    private VesselService vesselService;

    @GetMapping
    public ResponseEntity<List<Vessel>> getAllVessels() {
        return ResponseEntity.ok().body(vesselService.getAllVessels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vessel> getVesselById(@PathVariable Long id) {
        return ResponseEntity.ok().body(vesselService.getVesselById(id));
    }

    @PostMapping
    public ResponseEntity<Vessel> createVessel(@RequestBody Vessel vessel) throws Exception {
        return ResponseEntity.ok().body(this.vesselService.creatreVessel(vessel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vessel> updateVessel(@PathVariable long id, @RequestBody Vessel vessel) {
        vessel.setId(id);
        return ResponseEntity.ok().body(this.vesselService.updateVessel(vessel));
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteVessel(@PathVariable long id) {
        this.vesselService.deleteVessel(id);
        return HttpStatus.OK;
    }
}
