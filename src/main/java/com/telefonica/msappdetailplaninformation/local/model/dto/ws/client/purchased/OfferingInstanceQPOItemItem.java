package com.telefonica.msappdetailplaninformation.local.model.dto.ws.client.purchased;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OfferingInstanceQPOItemItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private String idParameter;
    private String statusDetailInfo;
    private String idBundledProductOffering;
    private String customerStatusTime;
    private String relaPrimaryOfferingInstanceIdInfo;
    private String endDateTimeTimePeriod;
    private String startDateTimeTimePeriod;
    private String nameProductOffering;
    private String offeringDescriptionInfo;
    private List<PropertyQPOItem> propertyQPOItem;
    private String interactionStatusBusinessInteraction;
    private String shortNameProductOffering;
    private SalesInformationQPOItem salesInformationQPOItem;
    private String brandInfo;
    private String categoryProductOffering;
    private String secondReqFlag;
    private String secondReqAuxFlag;
}