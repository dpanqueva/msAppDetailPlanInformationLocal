package com.telefonica.msappdetailplaninformation.local.process;

import com.telefonica.msappdetailplaninformation.local.exceptions.NoContentException;
import com.telefonica.msappdetailplaninformation.local.model.dto.ws.client.subscriber.info.RSSubsWrapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Processor for subscriberInformation consume
 */
public class SubscriberProcessor implements Processor {

    /**
     * method for process information
     */
    @Override
    public void process(Exchange exchange) throws Exception {
        /** convert response to object and save into properties map*/
        RSSubsWrapper rsSubsWrapper = exchange.getIn().getBody(RSSubsWrapper.class);

        if (rsSubsWrapper == null) {
            throw new NoContentException("No information found, fsGetSubscriberList service");
        }

        if (rsSubsWrapper.getServiceResponse() == null) {
            throw new NoContentException("No information found, fsGetSubscriberList service");
        }

        if (rsSubsWrapper.getServiceResponse().getSubscriberProductItem() == null) {
            throw new NoContentException("No information found, fsGetSubscriberList service");
        }
        exchange.setProperty("obj-subs-info", rsSubsWrapper);
    }
}
