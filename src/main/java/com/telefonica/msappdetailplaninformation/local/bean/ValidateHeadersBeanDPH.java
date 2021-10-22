package com.telefonica.msappdetailplaninformation.local.bean;

import com.telefonica.msappdetailplaninformation.local.exceptions.BadRequestException;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidateHeadersBeanDPH {

    private static final Pattern PATTERN_EVALUATE = Pattern
            .compile("[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}");

    private static final String ExecID = "execId";

    @Handler
    public void validateInputHeaders(Exchange exchange) throws BadRequestException {
        Map<String, Object> headers = exchange.getIn().getHeaders();
        evaluateHeaders(headers);
    }

    private void evaluateHeaders(Map<String, Object> headers) throws BadRequestException {
        List<String> lstHeaders = Arrays.asList("originator", "sender", "userId", "operation", ExecID);
        for (int i = 0; i < lstHeaders.size(); i++) {
            if (!headers.containsKey(lstHeaders.get(i))) {
                throw new BadRequestException("El header " + lstHeaders.get(i) + " es obligatorio");
            }
            if (lstHeaders.get(i).equalsIgnoreCase(ExecID)) {
                if (!validateExecId(headers.get(ExecID).toString())) {
                    throw new BadRequestException("Header no valid for pattern for this request: " .concat(lstHeaders.get(i)));
                }
            }
            validateString(headers.get(lstHeaders.get(i)).toString(), lstHeaders.get(i));
        }
    }

    private static void validateString(String value, String headerName) throws BadRequestException {
        if (value == null || value.isEmpty() || value.equalsIgnoreCase("null") || value.equals("")) {
            throw new BadRequestException("El header " + headerName + " es obligatorio");
        }
    }

    private boolean validateExecId(String execid) {
        boolean check = false;
        if (execid != null) {
            Matcher m = PATTERN_EVALUATE.matcher(execid);
            if (m.matches()) {
                check = true;
            }
        }
        return check;
    }
}
