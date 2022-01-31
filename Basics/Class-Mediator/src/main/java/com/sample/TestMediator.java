package com.sample;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

/**
 * This is a sample class mediator  that log for message flows.
 */

public class TestMediator extends AbstractMediator {


    private static final Log log = LogFactory.getLog(TestMediator.class);

    public boolean mediate(MessageContext messageContext) {
        log.info("Class mediator invoked");
        return true;
    }

    public String getType() {
        return null;
    }

    public void setTraceState(int traceState) {
        traceState = 0;
    }

    public int getTraceState() {
        return 0;
    }
}