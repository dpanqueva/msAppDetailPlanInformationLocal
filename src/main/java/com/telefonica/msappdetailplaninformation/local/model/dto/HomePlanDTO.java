package com.telefonica.msappdetailplaninformation.local.model.dto;

import lombok.Data;

import java.util.List;

public @Data class HomePlanDTO{

	private String planType;
	private String planName;
	private String subscriptionNumberPlan;
	private String accountNumberPlan;
	private String planCode;
	private int planValueTotal;
	private String installationAddress;
	private String activateDatePlan;
	private List<PrincipalProductsItemDTO> principalProducts;
	private List<SupplementaryProductsItemDTO> supplementaryProducts;
}