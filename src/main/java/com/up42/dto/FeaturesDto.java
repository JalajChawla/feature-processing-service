package com.up42.dto;

import lombok.Data;

/**
 * @author jalajchawla
 */
@Data
public class FeaturesDto {
    private String id;
    private Long  timestamp;
    private Long  beginViewingDate;
    private Long  endViewingDate;
    private String missionName;

}
