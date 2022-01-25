package com.up42.dto;

import lombok.Data;

import java.sql.Blob;
@Data
public class PropertyDto {
    private String id;
    private long timeStamp;
    private Blob quicklook;
    private AcquisitionDto acquisition;
}
