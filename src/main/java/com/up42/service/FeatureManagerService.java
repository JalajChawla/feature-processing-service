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

    FeaturesDto fetchFeaturesById(Integer id) throws FeatureNotFoundException;

    BufferedImage fetchFeaturesImageById(Integer id) throws FeatureNotFoundException;
}
