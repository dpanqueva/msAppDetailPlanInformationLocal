package com.telefonica.msappdetailplaninformation.local.model.dto.ws.client.subscriber.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author dpanquev
 * @version 2021-10-14
 *
 * */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubscriberWrapperDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("subscriberProductItem")
    private SubscriberProductItemsDTO subscriberProductItem;

}
