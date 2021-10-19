package com.telefonica.msappdetailplaninformation.local.model.dto.ws.client.invoice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QueryInvoice implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<InnvoiceResponseItem> innvoiceResponse;
    private String accountNumberCustomerAccount;
    private String serviceNumberAccount;
}