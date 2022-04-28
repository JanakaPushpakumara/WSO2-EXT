package com.sample;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

import javax.swing.plaf.IconUIResource;

/**
 * This is a sample class mediator  that log for message flows.
 */

public class TestMediator extends AbstractMediator {


    private static final Log log = LogFactory.getLog(TestMediator.class);

    public boolean mediate(MessageContext messageContext) {
        log.debug("Class mediator invoked");
        spin(Integer.parseInt(messageContext.getProperty("Delay").toString()));
        return true;
    }
    // CPU trap
    private static void spin(int milliseconds) {
        long sleepTime = milliseconds*1000000L; // convert to nanoseconds
        long startTime = System.nanoTime();
        while ((System.nanoTime() - startTime) < sleepTime) {
            long count = 0l;
            for(long x=0;x<Integer.MAX_VALUE ;x++){
                count+=1;
            }
        }
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