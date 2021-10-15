package com.telefonica.msappdetailplaninformation.local.routes;

import com.telefonica.msappdetailplaninformation.local.model.service.SubscriberService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;

@Component
public class RestConsumeRoute extends RouteBuilder {

    @Value("${camel.component.servlet.mapping.context-path}")
    private String contextPath;

    @Autowired
    private SubscriberService subscriberService;

    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet")
                .enableCORS(true)
                .host("localhost").port("7001")
                .contextPath(contextPath.substring(0,contextPath.length() - 2))
                .apiContextPath("/api-doc")
                .apiProperty("api.description", "prueba")
                .apiProperty("api.title", "{{spring.application.name}}")
                .apiProperty("api.version", "1.0.0")
                .bindingMode(RestBindingMode.json);

        rest("/api/v1").get("/hellow-world").produces(MediaType.APPLICATION_JSON).route().setBody(constant("Hola Mundo"));

        //rest().get("/subscriber").produces(MediaType.APPLICATION_JSON).route().setBody(()->subscriberService.subscriberAll());

        rest().get("/subscriberInfo")
                //.route().routeId("rest-subscriber-info")
                //.to("direct:consumeWsGetSubscriber")
                //.removeHeader("CamelHttp*")
                .id("consumir-enpoint")
                .produces(MediaType.APPLICATION_JSON)
                .param()
                .name("system")
                .type(RestParamType.header)
                .required(true) // false
                .endParam()
                /*.setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .setHeader("system", constant("S"))
                .setHeader("operation", constant("a"))
                .setHeader("execid", constant("s"))
                .setHeader("timestamp", constant("2021"))
                .setHeader("msgtype", constant("REQUEST"))*/
                .to("direct-vm:consumeWsGetSubscriber")
        //.setBody(() -> subscriberService.subscriberAll())
        //.log("response WS REST : ${body}")
        ;


        //


    }
}
