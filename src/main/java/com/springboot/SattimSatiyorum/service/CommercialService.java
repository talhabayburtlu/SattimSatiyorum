package com.springboot.SattimSatiyorum.service;

import com.springboot.SattimSatiyorum.entity.Commercial;

public interface CommercialService {

    Commercial findById(int commercialId);

    void save(Commercial commercial);

    void deleteById(int commercialId);

}
