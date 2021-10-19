package com.telefonica.msappdetailplaninformation.local.model.dto.ws.client.subscriber.info;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.telefonica.msappdetailplaninformation.local.model.dto.EResponseType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author dpanquev
 * @version 0.0.1
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RSSubsWrapper implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("serviceResponse")
    private SubscriberWrapperDTO serviceResponse;
    private EResponseType type;
    private String message;
    private String code;

}
