package com.telefonica.msappdetailplaninformation.local.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PrincipalProductsItemDTO {

    private String productNumber;
    private String productType;
    private String productName;
}