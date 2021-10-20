package com.telefonica.msappdetailplaninformation.local.process;

import com.telefonica.msappdetailplaninformation.local.exceptions.NoContentException;
import com.telefonica.msappdetailplaninformation.local.model.dto.ws.client.invoice.RSInvoiceWrapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class InvoiceProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        /** convert response to object and save into properties map*/
        RSInvoiceWrapper rsInvoiceWrapper = exchange.getIn().getBody(RSInvoiceWrapper.class);

        if (rsInvoiceWrapper == null) {
            throw new NoContentException("No information found, msInvoiceInformation service");
        }

        if (rsInvoiceWrapper.getQueryInvoice() == null) {
            throw new NoContentException("No information found, msInvoiceInformation service");
        }

        if (rsInvoiceWrapper.getQueryInvoice().getInnvoiceResponse() == null) {
            throw new NoContentException("No information found, msInvoiceInformation service");
        }

        if (rsInvoiceWrapper.getQueryInvoice().getInnvoiceResponse().isEmpty()) {
            throw new NoContentException("No information found, msInvoiceInformation service");
        }

        exchange.setProperty("obj-invoice", rsInvoiceWrapper);
    }
}
