package com.sample;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.synapse.rest.Handler;
import org.apache.synapse.MessageContext;
import org.apache.synapse.core.axis2.Axis2MessageContext;

import java.util.Map;

/**
 * This is a sample handler that log for all message flows.
 */
public class RestAPIHandler implements Handler {
    private static final Log log = LogFactory.getLog(RestAPIHandler.class);

    // Handle request message flow of the WSO2 server
    @Override
    public boolean handleRequest(MessageContext messageContext) {
        log.info("Executing handleRequest");
        return true;
    }
    // Handle response message flow of the WSO2 server
    @Override
    public boolean handleResponse(MessageContext messageContext) {
        log.info("Executing handleResponse");
        return true;
    }

    @Override
    public void addProperty(String s, Object o) {

    }

    @Override
    public Map getProperties() {
        return null;
    }
}