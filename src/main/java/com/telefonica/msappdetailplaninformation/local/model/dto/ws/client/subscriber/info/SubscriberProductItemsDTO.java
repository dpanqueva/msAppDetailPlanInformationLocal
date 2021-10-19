package com.telefonica.msappdetailplaninformation.local.model.dto.ws.client.subscriber.info;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


/**
 * @author dpanquev
 * @version 2021-10-14
 * */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriberProductItemsDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * startDateTimeTimePeriod from subscriberInformationItem
     */
    @JsonProperty("activeDate")
    private String activeDate;

    /**
     * idProductOffering from primaryOfferingItem object
     */
    private String planCode;

    /**
     * nameProductOffering from primaryOfferingItem object
     */
    private String planName;

    /**
     * nameProductOffering from primaryOfferingItem object
     */
    private String planType;

    /**
     * basic products for general plan
     * */
    private List<SubscriberProductItemDescDTO> subscriberProductItemDesc;


}
