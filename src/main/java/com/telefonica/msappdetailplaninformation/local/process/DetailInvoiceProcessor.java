package com.telefonica.msappdetailplaninformation.local.process;

import com.telefonica.msappdetailplaninformation.local.model.dto.EResponseType;
import com.telefonica.msappdetailplaninformation.local.model.dto.ResponseDTO;
import com.telefonica.msappdetailplaninformation.local.model.dto.detail.DetailInvoiceInfoDTO;
import com.telefonica.msappdetailplaninformation.local.model.dto.ws.client.invoice.RSInvoiceWrapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class DetailInvoiceProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        /** convert response to object and save into properties map*/
        RSInvoiceWrapper rsInvoiceWrapper = exchange.getIn().getBody(RSInvoiceWrapper.class);
        String invoiceAmount = (rsInvoiceWrapper.getQueryInvoice().getInnvoiceResponse() != null) ?
                (rsInvoiceWrapper.getQueryInvoice().getInnvoiceResponse().get(0) != null) ?
                        rsInvoiceWrapper.getQueryInvoice().getInnvoiceResponse().get(0).getInvoiceAmount() : ""
                : "";
        String accountNumberCustomerAccount = (rsInvoiceWrapper.getQueryInvoice() != null) ?
                (rsInvoiceWrapper.getQueryInvoice().getAccountNumberCustomerAccount() != null) ?
                        rsInvoiceWrapper.getQueryInvoice().getAccountNumberCustomerAccount() : "" : "";
        String serviceNumber = (rsInvoiceWrapper.getQueryInvoice() != null) ?
                (rsInvoiceWrapper.getQueryInvoice().getServiceNumberAccount() != null) ?
                        rsInvoiceWrapper.getQueryInvoice().getServiceNumberAccount() : "" : "";

        DetailInvoiceInfoDTO detailInvoiceInfoDTO = new DetailInvoiceInfoDTO(invoiceAmount, accountNumberCustomerAccount, serviceNumber);
        exchange.getIn().setBody(new ResponseDTO(detailInvoiceInfoDTO, EResponseType.SUCCESS,"Information found","200"));

    }
}
