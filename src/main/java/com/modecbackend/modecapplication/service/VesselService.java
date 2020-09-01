package com.modecbackend.modecapplication.service;

import com.modecbackend.modecapplication.model.Vessel;

import java.util.List;

public interface VesselService {
    Vessel creatreVessel(Vessel vessel) throws Exception;

    Vessel updateVessel(Vessel vessel);

    List<Vessel> getAllVessels();

    Vessel getVesselById(long vesselId);

    void deleteVessel(long id);
}
