package com.telefonica.msappdetailplaninformation.local.process;

import com.telefonica.msappdetailplaninformation.local.model.dto.ws.client.purchased.RSPurchasedWrapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class PurchasedProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        /** convert response to object and save into properties map*/
        RSPurchasedWrapper rsPurchasedWrapper = exchange.getIn().getBody(RSPurchasedWrapper.class);
        exchange.setProperty("obj-purchased", rsPurchasedWrapper);
        // guardar los bodys dentro de los properties para obtenerlos y armar la respuesta
    }

}
