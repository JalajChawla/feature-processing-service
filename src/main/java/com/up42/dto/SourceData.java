package com.up42.dto;

import lombok.Builder;

import java.util.List;

/**
 * @author jalajchawla
 */
@Builder
public class SourceData {
    private String type;
    private List<FeatureDto> features;
}
