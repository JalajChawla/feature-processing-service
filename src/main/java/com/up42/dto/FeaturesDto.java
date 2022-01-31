package com.up42.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author jalajchawla
 */
@Data
public class FeaturesDto implements Serializable {
    private String id;
    private Long  timestamp;
    private Long  beginViewingDate;
    private Long  endViewingDate;
    private String missionName;
}
