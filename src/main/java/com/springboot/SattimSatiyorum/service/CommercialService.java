package com.springboot.SattimSatiyorum.service;

import com.springboot.SattimSatiyorum.entity.Commercial;

import java.sql.Date;
import java.util.ArrayList;

public interface CommercialService {

    Commercial findById(int commercialId);

    void save(Commercial commercial);

    void deleteById(int commercialId);

    ArrayList<Commercial> findActiveCommercials(int page);

    ArrayList<Commercial> findActiveCommercialsByFeatureOptionIds(int page, ArrayList<Integer> featureOptionIds);

    ArrayList<Commercial> findActiveCommercialsByDate(int page, Date date);

    ArrayList<Commercial> findActiveCommercialsByPrice(int page, int min, int max);

    ArrayList<Commercial> findActiveCommercialsByIsUrgent(int page);
}
