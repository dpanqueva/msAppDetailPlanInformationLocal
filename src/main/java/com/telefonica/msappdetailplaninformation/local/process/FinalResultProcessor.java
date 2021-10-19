package com.telefonica.msappdetailplaninformation.local.process;

import com.telefonica.msappdetailplaninformation.local.model.dto.*;
import com.telefonica.msappdetailplaninformation.local.model.dto.ws.client.invoice.RSInvoiceWrapper;
import com.telefonica.msappdetailplaninformation.local.model.dto.ws.client.purchased.RSPurchasedWrapper;
import com.telefonica.msappdetailplaninformation.local.model.dto.ws.client.subscriber.info.RSSubsWrapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.ArrayList;
import java.util.List;

public class FinalResultProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        /** convert response to object and save into properties map*/
        RSSubsWrapper rsSubsWrapper = (RSSubsWrapper) exchange.getProperty("obj-subs-info");
        RSPurchasedWrapper rsPurchasedWrapper = (RSPurchasedWrapper) exchange.getProperty("obj-purchased");
        RSInvoiceWrapper rsInvoiceWrapper = (RSInvoiceWrapper) exchange.getProperty("obj-invoice");

        String accountNumberPlan =
                (rsInvoiceWrapper.getQueryInvoice().getAccountNumberCustomerAccount() != null) ?
                        rsInvoiceWrapper.getQueryInvoice().getAccountNumberCustomerAccount()
                        : rsInvoiceWrapper.getQueryInvoice().getServiceNumberAccount();

        String planName = (rsSubsWrapper.getServiceResponse().getSubscriberProductItem().getPlanName() != null) ?
                rsSubsWrapper.getServiceResponse().getSubscriberProductItem().getPlanName() : "";
        String planCode = (rsSubsWrapper.getServiceResponse().getSubscriberProductItem().getPlanCode() != null) ?
                rsSubsWrapper.getServiceResponse().getSubscriberProductItem().getPlanCode() : "";
        String planType = (rsSubsWrapper.getServiceResponse().getSubscriberProductItem().getPlanType() != null) ?
                rsSubsWrapper.getServiceResponse().getSubscriberProductItem().getPlanType() : "";

        String subscriptionNumberPlan = (rsInvoiceWrapper.getQueryInvoice().getServiceNumberAccount() != null) ?
                rsInvoiceWrapper.getQueryInvoice().getServiceNumberAccount() : "";

        String installationAddress = "";
        String activateDatePlan =
                (rsSubsWrapper.getServiceResponse().getSubscriberProductItem().getActiveDate() != null) ?
                        rsSubsWrapper.getServiceResponse().getSubscriberProductItem().getActiveDate() : "";

        String planValueTotal = (rsInvoiceWrapper.getQueryInvoice().getInnvoiceResponse().get(0).getInvoiceAmount() != null) ?
                rsInvoiceWrapper.getQueryInvoice().getInnvoiceResponse().get(0).getInvoiceAmount() : "0";

        HomePlanDTO homePlanDTO = new HomePlanDTO();
        homePlanDTO.setPlanName(planName);
        homePlanDTO.setPlanCode(planCode);
        homePlanDTO.setPlanType(planType);
        homePlanDTO.setSubscriptionNumberPlan(subscriptionNumberPlan);
        homePlanDTO.setAccountNumberPlan(accountNumberPlan);
        homePlanDTO.setInstallationAddress(installationAddress);
        homePlanDTO.setActivateDatePlan(activateDatePlan);
        homePlanDTO.setPlanValueTotal(Double.valueOf(planValueTotal));

        /**
         * obtain principal products
         * */
        List<PrincipalProductsItemDTO> lstPrincipalProductsItemDTO = new ArrayList<>();
        rsSubsWrapper.getServiceResponse().getSubscriberProductItem().getSubscriberProductItemDesc().forEach(pp ->
                lstPrincipalProductsItemDTO.add(new PrincipalProductsItemDTO(pp.getOfferingId(), pp.getNetWorkType(),
                        pp.getOfferingName()))
        );
        homePlanDTO.setPrincipalProducts(lstPrincipalProductsItemDTO);

        /**
         * obtain supplementary products
         * */
        List<SupplementaryProductsItemDTO> lstSupplementaryProductsItemDTO = new ArrayList<>();
        rsPurchasedWrapper.getQueryPurchasedOfferingResponse().getRspBodyQPOItem().getOfferingInstanceQPOItem()
                .forEach(qp -> {
                    String value = (qp.getPropertyQPOItem().size() > 1) ? qp.getPropertyQPOItem().stream().filter(q -> q.getIdParameter().equalsIgnoreCase("OFFER_PRICE"))
                            .findFirst().orElse(null).getValueParameter() : "";
                    lstSupplementaryProductsItemDTO.add(new SupplementaryProductsItemDTO(qp.getNameProductOffering(), qp.getOfferingDescriptionInfo()
                            , "", qp.getEndDateTimeTimePeriod(), "", value));
                });

        homePlanDTO.setSupplementaryProducts(lstSupplementaryProductsItemDTO);


        /** save new response and assign to body*/
        exchange.getIn().setBody(new ResponseDTO(homePlanDTO, EResponseType.SUCCESS, "SUCCESS", "200"));

        // guardar los bodys dentro de los properties para obtenerlos y armar la respuesta
    }
}
