package com.telefonica.msappdetailplaninformation.local.bean;

import com.telefonica.msappdetailplaninformation.local.exceptions.BadRequestException;
import com.telefonica.msappdetailplaninformation.local.model.dto.EResponseType;
import com.telefonica.msappdetailplaninformation.local.model.dto.HomePlanDTO;
import com.telefonica.msappdetailplaninformation.local.model.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.camel.http.common.HttpOperationFailedException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ResponseHandlerDPH {


    @Handler
    public ResponseDTO buildHandlerException(Exchange exchange) {
        Object exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setServiceResponse(null);

        String messageResponse = "Consumed service responded with errors";
        if (exception instanceof HttpOperationFailedException) {
            String message = ((HttpOperationFailedException) exception).getMessage();
            String body = ((HttpOperationFailedException) exception).getResponseBody();
            log.error("Consumed service responded with errors: " .concat("message: ").concat(message)
                    .concat(" Body: ").concat(body));
            if (((HttpOperationFailedException) exception).getStatusCode() == 500) {
                responseDTO.setType(EResponseType.ERROR);
                responseDTO.setCode("500");
                responseDTO.setMessage(messageResponse);
                exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "500");
            } else if (((HttpOperationFailedException) exception).getStatusCode() == 400) {
                responseDTO.setType(EResponseType.BAD_REQUEST);
                responseDTO.setCode("400");
                responseDTO.setMessage(messageResponse);
                exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "400");
            } else {
                responseDTO.setType(EResponseType.ERROR);
                responseDTO.setCode("500");
                responseDTO.setMessage("Unexpected error");
                exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "500");
            }
        } if(exception instanceof BadRequestException ){
            responseDTO.setType(EResponseType.BAD_REQUEST);
            responseDTO.setCode("400");
            responseDTO.setMessage(((BadRequestException) exception).getMessage());
            exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "400");
        }else {
            responseDTO.setType(EResponseType.ERROR);
            responseDTO.setCode("500");
            responseDTO.setMessage("Unexpected error");
            exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "500");
        }
        return responseDTO;
    }
}
