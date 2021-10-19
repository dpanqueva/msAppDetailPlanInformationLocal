package com.telefonica.msappdetailplaninformation.local.model.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SupplementaryProductsItemDTO {
	private String supplementaryName;
	private String supplementaryDescription;
	private String supplementaryType;
	private String supplementaryEffectiveDate;
	private String supplementaryFree;
	private String supplementaryValue;
}