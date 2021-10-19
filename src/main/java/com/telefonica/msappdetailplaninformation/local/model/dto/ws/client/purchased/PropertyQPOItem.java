package com.telefonica.msappdetailplaninformation.local.model.dto.ws.client.purchased;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyQPOItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private String idParameter;

    private String valueParameter;
}
