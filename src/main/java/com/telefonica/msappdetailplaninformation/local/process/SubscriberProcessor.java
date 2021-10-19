package com.telefonica.msappdetailplaninformation.local.process;

import com.telefonica.msappdetailplaninformation.local.model.dto.ws.client.subscriber.info.RSSubsWrapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Processor for subscriberInformation consume
 * */
public class SubscriberProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        /** convert response to object and save into properties map*/
        RSSubsWrapper rsSubsWrapper = exchange.getIn().getBody(RSSubsWrapper.class);
        exchange.setProperty("obj-subs-info", rsSubsWrapper);

        // guardar objeto homePlan properties

        // guardar los bodys dentro de los properties para obtenerlos y armar la respuesta
    }
}
