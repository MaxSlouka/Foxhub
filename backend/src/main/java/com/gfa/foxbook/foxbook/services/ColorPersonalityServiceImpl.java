package com.gfa.foxbook.foxbook.services;

import com.gfa.foxbook.foxbook.models.nonusermodels.Personality;
import com.gfa.foxbook.foxbook.services.interfaces.ColorPersonalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorPersonalityServiceImpl implements ColorPersonalityService {

    private final ColorPersonalityService colorPersonalityService;

    @Autowired
    public ColorPersonalityServiceImpl(ColorPersonalityService colorPersonalityService) {
        this.colorPersonalityService = colorPersonalityService;
    }

    @Override
    public List<Personality> getAllColorPersonalities() {
        return colorPersonalityService.getAllColorPersonalities();
    }
}
