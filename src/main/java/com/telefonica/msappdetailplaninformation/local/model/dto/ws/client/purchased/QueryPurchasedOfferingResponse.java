package com.telefonica.msappdetailplaninformation.local.model.dto.ws.client.purchased;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QueryPurchasedOfferingResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private RspBodyQPOItem rspBodyQPOItem;
}