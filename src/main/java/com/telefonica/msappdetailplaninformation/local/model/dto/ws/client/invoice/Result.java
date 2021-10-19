package com.telefonica.msappdetailplaninformation.local.model.dto.ws.client.invoice;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;
	private String description;
}