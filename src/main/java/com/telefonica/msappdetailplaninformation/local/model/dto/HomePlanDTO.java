package com.telefonica.msappdetailplaninformation.local.model.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HomePlanDTO{

	private String planType; //
	private String planName; //
	private String subscriptionNumberPlan; //
	private String accountNumberPlan;//
	private String planCode;//
	private double planValueTotal;//
	private String installationAddress;//->falta
	private String activateDatePlan;//
	private List<PrincipalProductsItemDTO> principalProducts;
	private List<SupplementaryProductsItemDTO> supplementaryProducts;
}