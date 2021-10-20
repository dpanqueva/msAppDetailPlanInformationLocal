package com.telefonica.msappdetailplaninformation.local.routes;

import com.telefonica.msappdetailplaninformation.local.bean.ResponseHandlerDPH;
import com.telefonica.msappdetailplaninformation.local.bean.ValidateHeadersBeanDPH;
import com.telefonica.msappdetailplaninformation.local.exceptions.BadRequestException;
import com.telefonica.msappdetailplaninformation.local.exceptions.NoContentException;
import com.telefonica.msappdetailplaninformation.local.model.dto.ws.client.invoice.RSInvoiceWrapper;
import com.telefonica.msappdetailplaninformation.local.model.dto.ws.client.purchased.RSPurchasedWrapper;
import com.telefonica.msappdetailplaninformation.local.model.dto.ws.client.subscriber.info.RSSubsWrapper;
import com.telefonica.msappdetailplaninformation.local.process.*;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class RouteSubscriberInformation extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        onException(Exception.class)
                .handled(true)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(500))
                .log(LoggingLevel.ERROR, "Unexpected error")
                .bean(ResponseHandlerDPH.class)
                .marshal().json(JsonLibrary.Jackson)
                .log(LoggingLevel.INFO, "Build error")
                .process(new DPHHeaderResponseProcessor())
                .end();
        onException(BadRequestException.class)
                .handled(true)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
                .log(LoggingLevel.ERROR, "Bad request error")
                .bean(ResponseHandlerDPH.class)
                .marshal().json(JsonLibrary.Jackson)
                .log(LoggingLevel.INFO, "Build error")
                .process(new DPHHeaderResponseProcessor())
                .end();
        onException(NoContentException.class)
                .handled(true)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(204))
                .log(LoggingLevel.ERROR, "No Content")
                .bean(ResponseHandlerDPH.class)
                .marshal().json(JsonLibrary.Jackson)
                .log(LoggingLevel.INFO, "Build error")
                .process(new DPHHeaderResponseProcessor())
                .end();

        from("direct-vm:consumeWs")
                .routeId("all-service-subscriber")
                .removeHeader(Exchange.HTTP_PATH)
                .bean(new ValidateHeadersBeanDPH())
                //.bean(ValidateHeadersBean.class)
                //.removeHeader("CamelHttp*")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .setHeader("originator", constant("S"))
                .setHeader("userid", constant("a"))
                .setHeader("execid", constant("550e8400-e29b-41d4-a716-446655440001"))
                .setHeader("operation", constant("2021"))
                .setHeader("msgtype", constant("REQUEST"))
                //.recipientList(simple("http4://localhost:8001/telefonica/customer/v1/subscriberinformation/subscriberInfoByAccountId/${header.accountId}?bridgeEndpoint=true"))
                .toD("http4://localhost:8001/telefonica/customer/v1/subscriberinformation/subscriberInfoByAccountId/${header.accountId}?bridgeEndpoint=true")
                .unmarshal().json(JsonLibrary.Jackson, RSSubsWrapper.class)
                .process(new SubscriberProcessor())
                //.recipientList(simple("http4://f26af4c5-00af-4967-a49e-259c928dfdb5.mock.pstmn.io/okQueryPurchasedOffering?bridgeEndpoint=true"))
                .to("http4://f26af4c5-00af-4967-a49e-259c928dfdb5.mock.pstmn.io/okQueryPurchasedOffering?bridgeEndpoint=true")
                .unmarshal().json(JsonLibrary.Jackson, RSPurchasedWrapper.class)
                .process(new PurchasedProcessor())
                //.recipientList(simple("http4://a500f7b2-2189-4b2d-a4da-63424458349c.mock.pstmn.io/okInvoiceInformation?bridgeEndpoint=true"))
                .to("http4://a500f7b2-2189-4b2d-a4da-63424458349c.mock.pstmn.io/okInvoiceInformation?bridgeEndpoint=true")
                .unmarshal().json(JsonLibrary.Jackson, RSInvoiceWrapper.class)
                .process(new InvoiceProcessor())
                .process(new FinalResultProcessor())
                .log("response WS REST subscriberInformation : ${body}")
        ;

        from("direct-vm:consumeWsDetailInvoice")
                .routeId("all-service-detail-invoice")
                .removeHeader(Exchange.HTTP_PATH)
                //.bean(ValidateHeadersBean.class)
                //.removeHeader("CamelHttp*")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .setHeader("originator", constant("S"))
                .setHeader("userid", constant("a"))
                .setHeader("execid", constant("550e8400-e29b-41d4-a716-446655440001"))
                .setHeader("operation", constant("2021"))
                .setHeader("msgtype", constant("REQUEST"))
                //.recipientList(simple("http4://localhost:8001/telefonica/customer/v1/subscriberinformation/subscriberInfoByAccountId/${header.accountId}?bridgeEndpoint=true"))
                .toD("http4://a500f7b2-2189-4b2d-a4da-63424458349c.mock.pstmn.io/okInvoiceInformation?bridgeEndpoint=true")
                .unmarshal().json(JsonLibrary.Jackson, RSInvoiceWrapper.class)
                .process(new DetailInvoiceProcessor())
                .log("response WS REST subscriberInformation : ${body}")
        ;
    }
}
