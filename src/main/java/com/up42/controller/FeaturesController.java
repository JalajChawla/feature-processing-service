package com.up42.controller;

import com.up42.dto.FeaturesDto;
import com.up42.service.FeatureManagerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<FeaturesDto> fetchFeatureById(@PathVariable("id") Long id)throws Exception{
        FeaturesDto features=service.fetchFeaturesById(id);
        return new ResponseEntity<FeaturesDto>(features,HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value="{id}/quicklook", produces= MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> fetchFeaturesImageById(@PathVariable("id") Long id)throws Exception{
        byte[] b=service.fetchFeaturesImageById(id);
        return new ResponseEntity<byte[]>(b, HttpStatus.OK);
    }

}
