package com.up42.service;

import com.up42.dto.FeaturesDto;
import com.up42.entity.Features;
import com.up42.exception.FeatureNotFoundException;
import com.up42.repo.FeaturesRepository;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

/**
 * @author jalajchawla
 */
@Service
public class FeaturesManagerServiceImpl implements FeatureManagerService {
    private final FeaturesRepository featuresRepository;

    public FeaturesManagerServiceImpl(FeaturesRepository featuresRepository) {
        this.featuresRepository = featuresRepository;
    }

    @Override
    public FeaturesDto fetchFeaturesById(Long featureId) throws FeatureNotFoundException {
        Features feature = featuresRepository.findById(featureId)
                .orElseThrow(() -> new FeatureNotFoundException(featureId + " feature not found"));
        convertFeatureToFeatureDto(feature);
        return convertFeatureToFeatureDto(feature);
    }

    private FeaturesDto convertFeatureToFeatureDto(Features feature){
        FeaturesDto featuresDto = new FeaturesDto();
        featuresDto.setId(String.valueOf(feature.getId()));
        featuresDto.setTimestamp(feature.getTimestamp());
        featuresDto.setBeginViewingDate(feature.getBeginViewingDate());
        featuresDto.setEndViewingDate(feature.getEndViewingDate());
        featuresDto.setMissionName(feature.getMissionName());
        return featuresDto;
    }

    @Override
    public List<FeaturesDto> fetchAllFeatures() {
        List<Features> list=featuresRepository.findAll();
        List<FeaturesDto> featuresDtoList =new ArrayList<>();
        list.forEach(feature -> featuresDtoList.add(convertFeatureToFeatureDto(feature)));
        return featuresDtoList;
    }

    @Override
    public byte[] fetchFeaturesImageById(Long featureId) throws FeatureNotFoundException {
        Features feature = featuresRepository.findById(featureId)
                .orElseThrow(() -> new FeatureNotFoundException(featureId + " feature not found"));
        return decodeToImage(feature.getQuicklook());
    }

    private static byte[] decodeToImage(byte[] imageString) {
        byte[] decodedBytes = new byte[0];
        try {
             decodedBytes = Base64.getDecoder().decode(imageString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decodedBytes;
    }


}
