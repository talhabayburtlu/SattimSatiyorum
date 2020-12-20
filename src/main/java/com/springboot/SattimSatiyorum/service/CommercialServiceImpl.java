package com.springboot.SattimSatiyorum.service;

import com.springboot.SattimSatiyorum.dao.CommercialRepository;
import com.springboot.SattimSatiyorum.entity.Commercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        commercialRepository.save(commercial);
    }

    @Override
    public void deleteById(int commercialId) {
        commercialRepository.deleteById(commercialId);
    }
}
