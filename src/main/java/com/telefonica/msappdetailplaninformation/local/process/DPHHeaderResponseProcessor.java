package com.telefonica.msappdetailplaninformation.local.process;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class DPHHeaderResponseProcessor  implements Processor {

    public static final String HEADER_DESTINATION = "destination";
    public static final String HEADER_ORIGINATOR = "originator";
    public static final String HEADER_PID = "Pid";
    public static final String HEADER_EXECID = "execId";
    public static final String HEADER_MSGID = "msgId";
    public static final String HEADER_TIMESTAMP = "timestamp";
    public static final String HEADER_MSGTYPE = "msgType";
    public static final String HEADER_VARARG = "varArg";

    public static final String PATTERN_TIMESTAMP = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    /**
     * Method in charge of processing the response headers of the service.
     */
    @Override
    public void process(Exchange exchange) throws Exception {
        String originator = String.valueOf(exchange.getIn().getHeader(HEADER_ORIGINATOR));
        String execId = String.valueOf(exchange.getIn().getHeader(HEADER_EXECID));
        String msgId = String.valueOf(exchange.getIn().getHeader(HEADER_MSGID));

        exchange.getIn().removeHeaders("*", "CamelHttpResponseCode");

        exchange.getIn().setHeader(HEADER_ORIGINATOR, originator);
        exchange.getIn().setHeader(HEADER_PID, "");
        exchange.getIn().setHeader(HEADER_EXECID, execId);
        exchange.getIn().setHeader(HEADER_MSGID, msgId);
        exchange.getIn().setHeader(HEADER_MSGTYPE, "RESPONSE");
        exchange.getIn().setHeader(HEADER_VARARG, "");
        exchange.getIn().setHeader("Content-Type", "application/json;charset=UTF-8");
    }
}
