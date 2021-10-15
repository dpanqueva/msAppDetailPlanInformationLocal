package com.telefonica.msappdetailplaninformation.local.routes;

import com.telefonica.msappdetailplaninformation.local.process.TestProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class RouteTest extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("direct-vm:consumeWsGetSubscriber")
                .routeId("all-service-subscriber")
                .removeHeader("CamelHttp*")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .setHeader("system", constant("S"))
                .setHeader("operation", constant("a"))
                .setHeader("execid1", constant("s"))
                .setHeader("timestamp", constant("2021"))
                .setHeader("msgtype1", constant("REQUEST"))
                .recipientList(simple("http4://localhost:8001/telefonica/customer/v1/subscriberinformation/subscriberInfoByAccountId/123456?bridgeEndpoint=true"))
                .recipientList(simple("http4://localhost:8001/telefonica/customer/v1/subscriberinformation/subscriberInfoByAccountId/123456?bridgeEndpoint=true"))
                .recipientList(simple("http4://localhost:8001/telefonica/customer/v1/subscriberinformation/subscriberInfoByAccountId/123456?bridgeEndpoint=true"))
                .recipientList(simple("http4://localhost:8001/telefonica/customer/v1/subscriberinformation/subscriberInfoByAccountId/123456?bridgeEndpoint=true"))
                .unmarshal().json(JsonLibrary.Jackson)
                //.process(new TestProcessor())
                  //.toD("\"http4://localhost:8001/telefonica/customer/v1/subscriberinformation/subscriberInfoByAccountId/123456\"")
                //.setBody(() -> subscriberService.subscriberAll())
                .log("response WS REST : ${body}");
    }
}
