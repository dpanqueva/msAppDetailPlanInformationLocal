package com.telefonica.msappdetailplaninformation.local.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriberInformationDTO {

    private int id;

    private String product;

    private int price;
}
