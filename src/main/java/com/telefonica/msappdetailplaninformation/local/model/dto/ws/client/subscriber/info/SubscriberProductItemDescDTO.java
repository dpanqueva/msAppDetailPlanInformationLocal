package com.telefonica.msappdetailplaninformation.local.model.dto.ws.client.subscriber.info;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for describe primary products
 * @author dpanquev
 * @version 2021-10-14
 * */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriberProductItemDescDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * tipo de producto
     * **/
    private String netWorkType;

    private String offeringId;

    private String offeringName;
}
