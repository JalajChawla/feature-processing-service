package com.up42.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author jalajchawla
 */
@Data
public class PropertyDto {
    @JsonProperty("timestamp")
    private long timeStamp;
    private byte[] quicklook;
    private AcquisitionDto acquisition;
}
