package io.saagie.demo.extract.withings.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Category table
 * Category	Description
 * 1	Measure
 * 2	Target
 *
 * @author youen
 */
@Getter
@AllArgsConstructor
public enum Category
{
	MEASURE("Measure", 1),
	TARGET("Target", 2);

	private String description;
	private int value;

}
