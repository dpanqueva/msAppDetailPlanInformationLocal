package com.telefonica.msappdetailplaninformation.local.model.dto.detail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DetailInvoiceInfoDTO {

    private String invoiceAmount;

    private String accountNumberCustomerAccount;

    private String serviceNumber;
}
