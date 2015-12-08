package io.saagie.demo.extract.withings.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by youen
 */
@Data
@NoArgsConstructor
public class MeasureGRPS {

    private int grpid;
    private int attrib;
    private int category;
    private double date;
    private List<Measure> measure;
}
