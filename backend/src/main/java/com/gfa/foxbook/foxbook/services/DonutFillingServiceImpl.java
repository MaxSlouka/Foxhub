package com.gfa.foxbook.foxbook.services;

import com.gfa.foxbook.foxbook.models.nonusermodels.DonutFilling;
import com.gfa.foxbook.foxbook.repositories.DonutFillingRepository;
import com.gfa.foxbook.foxbook.services.interfaces.DonutFillingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonutFillingServiceImpl implements DonutFillingService {

    private final DonutFillingRepository donutFillingRepository;

    public DonutFillingServiceImpl(DonutFillingRepository donutFillingRepository) {
        this.donutFillingRepository = donutFillingRepository;
    }

    @Override
    public List<DonutFilling> getAllDonutFillings() {
        return donutFillingRepository.findAll();
    }
}
