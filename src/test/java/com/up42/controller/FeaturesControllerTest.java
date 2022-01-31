package com.up42.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
//import static org.mockito.Mockito.*;

public class FeaturesControllerTest {
    @Mock
    com.up42.service.FeatureManagerService service;
    @InjectMocks
    com.up42.controller.FeaturesController featuresController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFetchAllFeatures() throws Exception {
        when(service.fetchAllFeatures()).thenReturn(java.util.Arrays.<com.up42.dto.FeaturesDto>asList(new com.up42.dto.FeaturesDto()));

        org.springframework.http.ResponseEntity<java.util.List<com.up42.dto.FeaturesDto>> result = featuresController.fetchAllFeatures();
        Assert.assertEquals(null, result);
    }

    @Test
    public void testFetchFeatureById() throws Exception {
        when(service.fetchFeaturesById(anyLong())).thenReturn(new com.up42.dto.FeaturesDto());

        org.springframework.http.ResponseEntity<com.up42.dto.FeaturesDto> result = featuresController.fetchFeatureById(Long.valueOf(1));
        Assert.assertEquals(null, result);
    }

    @Test
    public void testFetchFeaturesImageById() throws Exception {
        when(service.fetchFeaturesImageById(anyLong())).thenReturn(new byte[]{(byte) 0});

        org.springframework.http.ResponseEntity<byte> result = featuresController.fetchFeaturesImageById(Long.valueOf(1));
        Assert.assertEquals(null, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme