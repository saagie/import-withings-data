package io.saagie.demo.extract.withings.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Measure group content fields
 * Name	Description
 * grpid	A unique grpid (Group Id), useful for performing synchronization tasks.
 * attrib	An attrib (Attribute), defining the way the measure was attributed to the user. It may take the values shown in the Attribution status table below.
 * date		The date (EPOCH format) at which the measure was taken or entered.
 * category	The category of the group. A measure group can represent either measures captured by the BodyScale or objectives set by the user. The category field indicates for each measure group whether the measures in the group are measurements or targets. Refer to the Category values table below.
 * measures	An array of measures that belong to this particular measure group.
 *
 * @author youen
 */
@Data
@NoArgsConstructor
public class MeasureGroup
{
	private int groupId;
	private AttributionStatus attributionStatus;
	private Date creationDate;
	private Category category;
	private List<Measure> measures;
}
