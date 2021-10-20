package com.telefonica.msappdetailplaninformation.local.bean;

import com.telefonica.msappdetailplaninformation.local.exceptions.BadRequestException;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class ValidateHeadersBeanDPH {


    @Handler
    public void validateInputHeaders(Exchange exchange) throws BadRequestException {
        Map<String, Object> headers = exchange.getIn().getHeaders();
        evaluateHeaders(headers);
    }

    private void evaluateHeaders(Map<String, Object> headers) throws BadRequestException {
        List<String> lstHeaders = Arrays.asList("originator", "sender", "userId", "operation", "execId");
        for (int i = 0; i < lstHeaders.size(); i++) {
            if (!headers.containsKey(lstHeaders.get(i))) {
                throw new BadRequestException("El header " + lstHeaders.get(i) + " es obligatorio");
            }
            validateString(headers.get(lstHeaders.get(i)).toString(), lstHeaders.get(i));
        }
    }

    private static void validateString(String value, String headerName) throws BadRequestException {
        if (value == null || value.isEmpty() || value.equalsIgnoreCase("null") || value.equals("")) {
            throw new BadRequestException("El header " + headerName + " es obligatorio");
        }
    }
}
