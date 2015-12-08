package io.saagie.demo.extract.withings.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by youen
 */
@Data
@NoArgsConstructor
public class Withings {
    private int status;
    private Body body;
}
