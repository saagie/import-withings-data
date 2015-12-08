package io.saagie.demo.extract.withings.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * Created by youen
 */
@Data
@NoArgsConstructor
public class Body {

    private int updatetime;
    private ArrayList<MeasureGroup> measuregrps;
    private String timezone;

}
