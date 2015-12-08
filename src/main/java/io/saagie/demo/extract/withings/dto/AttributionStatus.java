package io.saagie.demo.extract.withings.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Attribution status table
 * Attribution Mode	Description
 * 0	The measuregroup has been captured by a scale and is known to belong to this user (and is not ambiguous)
 * 1	The measuregroup has been captured by a scale but may belong to other users as well as this one (it is ambiguous)
 * 2	The measuregroup has been entered manually for this particular use
 * 4	The measuregroup has been entered manually during user creation (and may not be accurate)
 *
 * @author youen
 */
@Getter
@AllArgsConstructor
public enum AttributionStatus {
    NOT_AMBIGUOUS("The data has been captured by a scale and is known to belong to this user (and is not ambiguous)", 0),
    AMBIGUOUS("The data has been captured by a scale but may belong to other users as well as this one (it is ambiguous)", 1),
    MANUAL("The data has been entered manually for this particular use", 2),
    USER_CREATION("The data has been entered manually during user creation (and may not be accurate)", 4);

    private String description;
    private int value;
}
