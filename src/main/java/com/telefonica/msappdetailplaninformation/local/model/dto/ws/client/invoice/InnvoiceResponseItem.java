package com.telefonica.msappdetailplaninformation.local.model.dto.ws.client.invoice;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public  class InnvoiceResponseItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private String serviceAmount;
	private String billingPeriod;
	private String rechargeOriginAlt;
	private String invoiceSNPaymentInfoRel;
	private String invoiceAmount;
	private String deviceInstAmt;
	private String endDateTimePeriod;
}