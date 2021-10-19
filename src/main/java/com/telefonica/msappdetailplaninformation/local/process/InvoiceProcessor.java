package com.telefonica.msappdetailplaninformation.local.process;

import com.telefonica.msappdetailplaninformation.local.model.dto.ws.client.invoice.RSInvoiceWrapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class InvoiceProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        /** convert response to object and save into properties map*/
        RSInvoiceWrapper rsInvoiceWrapper = exchange.getIn().getBody(RSInvoiceWrapper.class);
        exchange.setProperty("obj-invoice", rsInvoiceWrapper);
        // guardar los bodys dentro de los properties para obtenerlos y armar la respuesta
    }
}
