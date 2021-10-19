package com.telefonica.msappdetailplaninformation.local.model.dto.ws.client.invoice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RSInvoiceWrapper implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("result")
	private Result result;
	@JsonProperty("queryInvoice")
	private QueryInvoice queryInvoice;
}