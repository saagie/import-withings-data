package io.saagie.demo.extract.withings.dto;

import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

/**
 * Measure types table
 * Type	Description
 * 1	Weight (Kg)
 * 4	Size (meter)
 * 5	Fat Free Mass (Kg)
 * 6	Fat Ratio (%)
 * 8	Fat Mass Weight (Kg)
 *
 * @author youen
 */
@AllArgsConstructor
@Getter
public enum MeasureType
{
	WEIGHT("Weight", 1),
	SIZE("Size", 4),
	FAT_FREE_MASS("Fat Free Mass", 5),
	FAT_RATIO("Fat Ratio (%)", 6),
	FAT_MASS_WEIGHT("Fat Mass Weight", 8);

	private static Map<Integer, MeasureType> measureTypeMap;
	private String description;
	private int type;


	public synchronized static Map<Integer, MeasureType> getMeasureTypes() {
		if (measureTypeMap == null) {
			ImmutableMap.Builder<Integer, MeasureType> mapBuilder = ImmutableMap.builder();
			for (MeasureType type : MeasureType.values()) {
				mapBuilder.put(type.getType(), type);
			}
			measureTypeMap = mapBuilder.build();
		}
		return measureTypeMap;
	}


}
