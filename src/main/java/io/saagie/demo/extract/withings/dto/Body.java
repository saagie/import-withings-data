package io.saagie.demo.extract.withings.dto;

import java.util.ArrayList;

/**
 * Created by youen on 02/12/2015.
 */
public class Body {

    public int updatetime;
    public ArrayList<MeasureGroup> measuregrps;
    public String timezone;

    @Override
    public String toString() {
        return "Body{" +
                "updatetime=" + updatetime +
                ", measuregrps=" + measuregrps +
                ", timezone='" + timezone + '\'' +
                '}';
    }
}
