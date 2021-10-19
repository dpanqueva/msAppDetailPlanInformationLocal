package com.telefonica.msappdetailplaninformation.local.routes;

import com.telefonica.msappdetailplaninformation.local.model.dto.ws.client.invoice.RSInvoiceWrapper;
import com.telefonica.msappdetailplaninformation.local.model.dto.ws.client.purchased.RSPurchasedWrapper;
import com.telefonica.msappdetailplaninformation.local.model.dto.ws.client.subscriber.info.RSSubsWrapper;
import com.telefonica.msappdetailplaninformation.local.process.FinalResultProcessor;
import com.telefonica.msappdetailplaninformation.local.process.InvoiceProcessor;
import com.telefonica.msappdetailplaninformation.local.process.PurchasedProcessor;
import com.telefonica.msappdetailplaninformation.local.process.SubscriberProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class RouteSubscriberInformation extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("direct-vm:consumeWs")
                .routeId("all-service-subscriber")
                .removeHeader("CamelHttp*")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .setHeader("originator", constant("S"))
                .setHeader("userid", constant("a"))
                .setHeader("execid", constant("550e8400-e29b-41d4-a716-446655440001"))
                .setHeader("operation", constant("2021"))
                .setHeader("msgtype1", constant("REQUEST"))
                .recipientList(simple("http4://localhost:8001/telefonica/customer/v1/subscriberinformation/subscriberInfoByAccountId/123456?bridgeEndpoint=true"))
                .unmarshal().json(JsonLibrary.Jackson, RSSubsWrapper.class)
                .process(new SubscriberProcessor())
                .recipientList(simple("http4://f26af4c5-00af-4967-a49e-259c928dfdb5.mock.pstmn.io/okQueryPurchasedOffering?bridgeEndpoint=true"))
                .unmarshal().json(JsonLibrary.Jackson, RSPurchasedWrapper.class)
                .process(new PurchasedProcessor())
                .recipientList(simple("http4://a500f7b2-2189-4b2d-a4da-63424458349c.mock.pstmn.io/okInvoiceInformation?bridgeEndpoint=true"))
                .unmarshal().json(JsonLibrary.Jackson, RSInvoiceWrapper.class)
                .process(new InvoiceProcessor())
                .process(new FinalResultProcessor())
                .log("response WS REST subscriberInformation : ${body}");
    }
}
