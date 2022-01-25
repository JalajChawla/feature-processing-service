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
    public FeaturesDto fetchFeaturesById(Integer featureId) throws FeatureNotFoundException {
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
    public BufferedImage fetchFeaturesImageById(Integer featureId) throws FeatureNotFoundException {
        Features feature = featuresRepository.findById(featureId)
                .orElseThrow(() -> new FeatureNotFoundException(featureId + " feature not found"));
        return decodeToImage(String.valueOf(feature.getQuicklook()));
    }

    private static BufferedImage decodeToImage(String imageString) {
        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
    }
    return image;
}
}
