package com.telefonica.msappdetailplaninformation.local.process;

import com.telefonica.msappdetailplaninformation.local.exceptions.NoContentException;
import com.telefonica.msappdetailplaninformation.local.model.dto.ws.client.purchased.RSPurchasedWrapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class PurchasedProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        /** convert response to object and save into properties map*/
        RSPurchasedWrapper rsPurchasedWrapper = exchange.getIn().getBody(RSPurchasedWrapper.class);

        if (rsPurchasedWrapper == null) {
            throw new NoContentException("No information found, fsQueryPurchasedOffering service");
        }

        if (rsPurchasedWrapper.getQueryPurchasedOfferingResponse() == null) {
            throw new NoContentException("No information found, fsQueryPurchasedOffering service");
        }

        if (rsPurchasedWrapper.getQueryPurchasedOfferingResponse().getRspBodyQPOItem() == null) {
            throw new NoContentException("No information found, fsQueryPurchasedOffering service");
        }

        if (rsPurchasedWrapper.getQueryPurchasedOfferingResponse().getRspBodyQPOItem().getOfferingInstanceQPOItem() == null) {
            throw new NoContentException("No information found, fsQueryPurchasedOffering service");
        }

        if (rsPurchasedWrapper.getQueryPurchasedOfferingResponse().getRspBodyQPOItem().getOfferingInstanceQPOItem().isEmpty()) {
            throw new NoContentException("No information found, fsQueryPurchasedOffering service");
        }
        exchange.setProperty("obj-purchased", rsPurchasedWrapper);
    }

}
