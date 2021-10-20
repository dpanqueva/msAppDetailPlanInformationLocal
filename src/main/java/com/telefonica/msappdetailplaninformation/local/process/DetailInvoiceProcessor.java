package com.telefonica.msappdetailplaninformation.local.process;

import com.telefonica.msappdetailplaninformation.local.model.dto.EResponseType;
import com.telefonica.msappdetailplaninformation.local.model.dto.ResponseDTO;
import com.telefonica.msappdetailplaninformation.local.model.dto.detail.DetailInvoiceInfoDTO;
import com.telefonica.msappdetailplaninformation.local.model.dto.ws.client.invoice.InnvoiceResponseItem;
import com.telefonica.msappdetailplaninformation.local.model.dto.ws.client.invoice.QueryInvoice;
import com.telefonica.msappdetailplaninformation.local.model.dto.ws.client.invoice.RSInvoiceWrapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class DetailInvoiceProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        /** convert response to object and save into properties map*/
        RSInvoiceWrapper rsInvoiceWrapper = exchange.getIn().getBody(RSInvoiceWrapper.class);
        QueryInvoice queryInvoice = (rsInvoiceWrapper.getQueryInvoice() != null) ? rsInvoiceWrapper.getQueryInvoice() :
                null;

        InnvoiceResponseItem innvoiceResponse = (queryInvoice != null && queryInvoice.getInnvoiceResponse() != null) ?
                queryInvoice.getInnvoiceResponse().get(0) : null;

        String invoiceAmount = (innvoiceResponse != null) ? innvoiceResponse.getInvoiceAmount() : "";

        String accountNumberCustomerAccount = (queryInvoice != null && queryInvoice.getAccountNumberCustomerAccount() != null) ?
                queryInvoice.getAccountNumberCustomerAccount() : "";


        String serviceNumber = (rsInvoiceWrapper.getQueryInvoice() != null && rsInvoiceWrapper.getQueryInvoice().getServiceNumberAccount() != null) ?
                rsInvoiceWrapper.getQueryInvoice().getServiceNumberAccount() : "";

        DetailInvoiceInfoDTO detailInvoiceInfoDTO = new DetailInvoiceInfoDTO(invoiceAmount, accountNumberCustomerAccount, serviceNumber);

        exchange.getIn().setBody(new ResponseDTO<DetailInvoiceInfoDTO>(detailInvoiceInfoDTO, EResponseType.SUCCESS, "Information found", "200"));

    }
}
