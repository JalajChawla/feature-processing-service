package com.up42.controller;

import com.up42.dto.FeaturesDto;
import com.up42.service.FeatureManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * @author jalajchawla
 */
@RestController
@RequestMapping("/features")
public class FeaturesController {
    private final FeatureManagerService service;

    public FeaturesController(FeatureManagerService service) {
        this.service = service;
    }

    @GetMapping("")
    public  ResponseEntity<List<FeaturesDto>>  fetchAllFeatures() throws Exception{
        List<FeaturesDto> featuresDtoList =service.fetchAllFeatures();
        return new ResponseEntity<List<FeaturesDto>>(featuresDtoList, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<FeaturesDto> fetchFeatureById(@PathVariable("id") Integer id)throws Exception{
        FeaturesDto features=service.fetchFeaturesById(id);
        return new ResponseEntity<FeaturesDto>(features,HttpStatus.OK);
    }

    @GetMapping("{id}/quicklook")
    public ResponseEntity<BufferedImage> fetchFeaturesImageById(@PathVariable("id") Integer id)throws Exception{
        BufferedImage image=service.fetchFeaturesImageById(id);
        return new ResponseEntity<BufferedImage>(image,HttpStatus.OK);
    }

}
