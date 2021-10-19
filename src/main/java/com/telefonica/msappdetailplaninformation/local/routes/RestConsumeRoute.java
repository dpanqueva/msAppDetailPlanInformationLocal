package com.telefonica.msappdetailplaninformation.local.routes;

import com.telefonica.msappdetailplaninformation.local.model.dto.ResponseDTO;
import com.telefonica.msappdetailplaninformation.local.model.service.SubscriberService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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
                .contextPath(contextPath.substring(0, contextPath.length() - 2))
                .apiContextPath("/api-doc")
                .apiProperty("api.description", "prueba")
                .apiProperty("api.title", "{{spring.application.name}}")
                .apiProperty("api.version", "1.0.0")
                .bindingMode(RestBindingMode.json);

        rest().get("{{exp.url.invoice.by.account.id}}")
                .description("Get query invoice information by accountId")
                .produces(MediaType.APPLICATION_JSON)
                .param()
                .name("accountId")
                .type(RestParamType.path)
                .required(true)
                .description("Number account id")
                .endParam()
                .responseMessage()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .responseModel(ResponseDTO.class)
                .endResponseMessage()
                .responseMessage()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.name())
                .responseModel(ResponseDTO.class)
                .endResponseMessage()
                .responseMessage()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .responseModel(ResponseDTO.class)
                .endResponseMessage()
                .to("direct-vm:consumeWsDetailInvoice")
        ;

        //rest().get("/subscriber").produces(MediaType.APPLICATION_JSON).route().setBody(()->subscriberService.subscriberAll());

        rest().get("{{exp.url.home.by.account.id}}")
                .description("Get detail plan information by accountId")
                //.route().routeId("rest-subscriber-info")
                //.to("direct:consumeWsGetSubscriber")
                //.removeHeader("CamelHttp*")
                .id("consumir-enpoint")
                .produces(MediaType.APPLICATION_JSON)
                .param()
                .name("accountId")
                .type(RestParamType.path)
                .required(true)
                .description("Number account id")
                .endParam()
                .responseMessage()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .responseModel(ResponseDTO.class)
                .endResponseMessage()
                .responseMessage()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.name())
                .responseModel(ResponseDTO.class)
                .endResponseMessage()
                .responseMessage()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .responseModel(ResponseDTO.class)
                .endResponseMessage()
                .to("direct-vm:consumeWs")

        ;


        //


    }
}
