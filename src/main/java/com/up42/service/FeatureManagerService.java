package com.up42.service;

import com.up42.dto.FeaturesDto;
import com.up42.exception.FeatureNotFoundException;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * @author jalajchawla
 */
public interface FeatureManagerService {
    List<FeaturesDto> fetchAllFeatures();

    FeaturesDto fetchFeaturesById(Long id) throws FeatureNotFoundException;

    byte[] fetchFeaturesImageById(Long id) throws FeatureNotFoundException;
}
