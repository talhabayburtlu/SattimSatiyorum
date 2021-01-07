package com.springboot.SattimSatiyorum.service;

import com.springboot.SattimSatiyorum.dao.CommercialRepository;
import com.springboot.SattimSatiyorum.entity.Commercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;

@Service
public class CommercialServiceImpl implements CommercialService {

    private final CommercialRepository commercialRepository;

    @Autowired
    public CommercialServiceImpl(CommercialRepository commercialRepository) {
        this.commercialRepository = commercialRepository;
    }

    @Override
    public Commercial findById(int commercialId) {
        Commercial commercial = commercialRepository.findById(commercialId).orElse(null);

        if (commercial == null)
            throw new RuntimeException("Commercial with id: " + commercialId + " can't found.");

        return commercial;
    }

    @Override
    public void save(Commercial commercial) {
        commercial.setCreatedAt(new Date((new java.util.Date().getTime())));
        commercial.setUpdatedAt(new Date((new java.util.Date().getTime())));
        commercialRepository.save(commercial);
    }

    @Override
    public void deleteById(int commercialId) {
        commercialRepository.deleteById(commercialId);
    }

    @Override
    public ArrayList<Commercial> findSoldCommercialsByActiveFromSeller(int page, int userId) {
        int perPage = 5;
        page = Math.max(page, 1);
        return (ArrayList<Commercial>) commercialRepository.findSoldCommercialsByActiveFromSeller(perPage * page - perPage, perPage * page, userId);
    }

    @Override
    public ArrayList<Commercial> findSoldCommercialsByNotActiveFromSeller(int page, int userId) {
        int perPage = 5;
        page = Math.max(page, 1);
        return (ArrayList<Commercial>) commercialRepository.findSoldCommercialsByNotActiveFromSeller(perPage * page - perPage, perPage * page, userId);
    }

    @Override
    public ArrayList<Commercial> findBoughtCommercialsFromBuyer(int page, int userId) {
        int perPage = 5;
        page = Math.max(page, 1);
        return (ArrayList<Commercial>) commercialRepository.findBoughtCommercialsFromBuyer(perPage * page - perPage, perPage * page, userId);
    }

    @Override
    public ArrayList<Commercial> findActiveCommercials(int page) {
        int perPage = 5;
        page = Math.max(page, 1);
        return (ArrayList<Commercial>) commercialRepository.findCommercialsByActive(perPage * page - perPage, perPage * page);
    }

    @Override
    public ArrayList<Commercial> findActiveCommercialsByFeatureOptionIds(int page, ArrayList<Integer> featureOptionIds) {
        int perPage = 5;
        page = Math.max(page, 1);
        return (ArrayList<Commercial>) commercialRepository.findCommercialsByFeatureOptionIds(perPage * page - perPage, perPage * page, featureOptionIds);
    }

    @Override
    public ArrayList<Commercial> findActiveCommercialsByDate(int page, Date date) {
        int perPage = 5;
        page = Math.max(page, 1);
        return (ArrayList<Commercial>) commercialRepository.findCommercialsByDate(perPage * page - perPage, perPage * page, date);
    }

    @Override
    public ArrayList<Commercial> findActiveCommercialsByPrice(int page, int min, int max) {
        int perPage = 5;
        page = Math.max(page, 1);
        return (ArrayList<Commercial>) commercialRepository.findCommercialsByPrice(perPage * page - perPage, perPage * page, min, max);
    }

    @Override
    public ArrayList<Commercial> findActiveCommercialsByIsUrgent(int page) {
        int perPage = 5;
        page = Math.max(page, 1);
        return (ArrayList<Commercial>) commercialRepository.findCommercialsByIsUrgent(perPage * page - perPage, perPage * page);
    }


}
