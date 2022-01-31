package com.up42.controller;

import com.up42.dto.FeatureDto;
import com.up42.dto.FeaturesDto;
import com.up42.service.FeatureManagerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.*;

public class FeaturesControllerTest {
    @Mock
    FeatureManagerService service;
    @InjectMocks
    FeaturesController featuresController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFetchAllFeatures() throws Exception {
        when(service.fetchAllFeatures()).thenReturn(Collections.<FeaturesDto>singletonList(new FeaturesDto()));

        ResponseEntity<List<FeaturesDto>> result = featuresController.fetchAllFeatures();
        Assert.assertEquals(1, Objects.requireNonNull(result.getBody()).size());
    }

    @Test
    public void testFetchFeatureById() throws Exception {
        when(service.fetchFeaturesById(anyLong())).thenReturn(new FeaturesDto());

        ResponseEntity<FeaturesDto> result = featuresController.fetchFeatureById(1L);
        Assert.assertEquals(new FeaturesDto().getId(), Objects.requireNonNull(result.getBody()).getId());
    }
}