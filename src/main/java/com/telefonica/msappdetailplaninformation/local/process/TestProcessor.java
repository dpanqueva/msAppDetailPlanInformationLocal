package com.telefonica.msappdetailplaninformation.local.process;

import com.telefonica.msappdetailplaninformation.local.model.dto.EResponseType;
import com.telefonica.msappdetailplaninformation.local.model.dto.ResponseDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

public class TestProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String obj = exchange.getIn().getBody(String.class);
        JsonParser parser = JsonParserFactory.getJsonParser();

        // guardar los bodys dentro de los properties para obtenerlos y armar la respuesta



        ResponseDTO response = new ResponseDTO(obj, EResponseType.SUCCESS, "ok", "bien");
        exchange.getIn().setBody(response);

    }
}
