package com.sample;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.synapse.AbstractSynapseHandler;
import org.apache.synapse.MessageContext;
import org.apache.synapse.core.axis2.Axis2MessageContext;

/**
 * This is a sample handler that log for all message flows.
 */
public class TestSynapseHandler extends AbstractSynapseHandler
{
    private static final Log log = LogFactory.getLog(TestSynapseHandler.class);

    // Handle incoming request message flow of the WSO2 server
    public boolean handleRequestInFlow(MessageContext messageContext)
    {
        log.info("Executing Request Inflow");
        return true;
    }

    public boolean handleRequestOutFlow(MessageContext messageContext)
    {
        log.info("Executing Request Outflow");
        return true;
    }

    // Handle outgoing response message flow of the WSO2 server
    public boolean handleResponseInFlow(MessageContext messageContext)
    {
        log.info("Executing Response Inflow");
        return true;
    }

    public boolean handleResponseOutFlow(MessageContext messageContext)
    {
        log.info("Executing Response Outflow");
        return true;
    }
}