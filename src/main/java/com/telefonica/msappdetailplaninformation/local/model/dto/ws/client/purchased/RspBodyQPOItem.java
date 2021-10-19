package com.telefonica.msappdetailplaninformation.local.model.dto.ws.client.purchased;

import java.io.Serializable;
import java.util.List;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RspBodyQPOItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<OfferingInstanceQPOItemItem> offeringInstanceQPOItem;
}