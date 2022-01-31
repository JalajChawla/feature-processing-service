package com.up42.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author jalajchawla
 */
@Entity
@Table(name="FEATURE_INFO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Features {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long  timestamp;
    private Long  beginViewingDate;
    private Long  endViewingDate;
    private String missionName;
    @Lob
    private byte[] quicklook;
}
