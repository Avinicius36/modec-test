package com.modecbackend.modecapplication.service;

import com.modecbackend.modecapplication.exception.ApiRequestException;
import com.modecbackend.modecapplication.model.Vessel;
import com.modecbackend.modecapplication.repository.VesselRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VesselServiceImpl implements VesselService {

    @Autowired
    private VesselRepository vesselRepository;

    @Override
    public Vessel creatreVessel(Vessel vessel) throws Exception {
        Vessel existentVessel = vesselRepository.findByCode(vessel.getCode());

        if (existentVessel != null && !existentVessel.equals(vessel)) {
            throw new ApiRequestException("This code already exists in Database, please consider changing the code");
        }
        return vesselRepository.save(vessel);
    }

    @Override
    public Vessel updateVessel(Vessel vessel) {
        Optional<Vessel> vesselDb = this.vesselRepository.findById(vessel.getId());

        if (vesselDb.isPresent()) {
            Vessel vesselupdate = vesselDb.get();
            vesselupdate.setId(vessel.getId());
            vesselupdate.setCode(vessel.getCode());

            List<Vessel> vesselsToValidate = vesselRepository.findAll();
            validateNames(vesselsToValidate, vesselupdate.getCode());

            vesselRepository.save(vesselupdate);

            return vesselupdate;
        } else {
            throw new ApiRequestException("Vessel not found with id: " + vessel.getId());
        }
    }

    private boolean validateNames(final List<Vessel> list, final String code) {
        Boolean checkingIfNameIsPresent = list.stream().filter(o -> o.getCode().equals(code)).findFirst().isPresent();
        if(checkingIfNameIsPresent == true) {
            throw new ApiRequestException("This code already exists in Database, please consider changing the code");
        } else {
            return false;
        }
    }

    @Override
    public List<Vessel> getAllVessels() {
        return this.vesselRepository.findAll();
    }

    @Override
    public Vessel getVesselById(long vesselId) {
        Optional<Vessel> vesselDb = this.vesselRepository.findById(vesselId);

        if (vesselDb.isPresent()) {
            return vesselDb.get();
        } else {
            throw new ApiRequestException("Vessel not found with id: " + vesselId);
        }
    }

    @Override
    public void deleteVessel(long vesselId) {
        Optional<Vessel> vesselDb = this.vesselRepository.findById(vesselId);

        if (vesselDb.isPresent()) {
            this.vesselRepository.delete(vesselDb.get());
        } else {
            throw new ApiRequestException("Vessel not found with id: " + vesselId);
        }
    }
}

