package com.up42.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Blob;


/**
 * @author jalajchawla
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Features {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Long  timestamp;
    private Long  beginViewingDate;
    private Long  endViewingDate;
    private String missionName;
    @Lob
    private Blob quicklook;
}
