package com.up42.service;

import com.up42.dto.FeatureDto;
import com.up42.dto.FeaturesDto;
import com.up42.dto.SourceData;
import com.up42.entity.Features;
import com.up42.exception.FeatureNotFoundException;
import com.up42.repo.FeaturesRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
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

    public void saveFeature(Features feature) {
         featuresRepository.save(feature);
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

    @Override
    public void save(List<SourceData> sourceList) {
        for(SourceData source:sourceList){
            List<FeatureDto> features = source.getFeatures();
            features.forEach(featureDto -> {
                long timeStamp = featureDto.getProperties().getTimeStamp();
                Features feature = new Features();
                feature.setTimestamp(timeStamp);
                feature.setBeginViewingDate(featureDto.getProperties().getAcquisition().getBeginViewingDate());
                feature.setEndViewingDate(featureDto.getProperties().getAcquisition().getEndViewingDate());
                feature.setMissionName(featureDto.getProperties().getAcquisition().getMissionName());
                feature.setQuicklook(featureDto.getProperties().getQuicklook());
                saveFeature(feature);
            });
        }
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
