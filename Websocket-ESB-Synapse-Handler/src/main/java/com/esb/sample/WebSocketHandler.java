package com.esb.sample;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.synapse.AbstractSynapseHandler;
import org.apache.synapse.MessageContext;

import java.util.UUID;

/**
 * This is a sample handler that log for Websocket message flow.
 */
public class WebSocketHandler extends AbstractSynapseHandler
{
    private static final Log log = LogFactory.getLog(WebSocketHandler.class);

    // Handle incoming request message flow of the WSO2 server
    public boolean handleRequestInFlow(MessageContext messageContext)
    {
        //log.info("Executing Request Inflow");
        // Filter out the websocket calls
        if(messageContext.getProperty("isInbound")!=null && messageContext.getProperty("isInbound").equals(true)){
            UUID uuid = UUID.randomUUID();
            //Setting uuid into the synapse Message Context
            messageContext.setProperty("WebActivityID", uuid.toString());

            //Setting uuid into the  Axis2 Message Context
/*            Axis2MessageContext axis2smc = (Axis2MessageContext) messageContext;
            org.apache.axis2.context.MessageContext axis2MessageCtx = axis2smc.getAxis2MessageContext();
            axis2MessageCtx.setProperty("WebActivityID", uuid.toString());*/
            log.info("Request Inflow websocket.subscriber.path : " +
                    messageContext.getProperty("websocket.subscriber.path") + ", WebActivityID :" +  uuid );
        }
        return true;
    }

    public boolean handleRequestOutFlow(MessageContext messageContext)
    {
        //log.info("Executing Request Outflow");
        return true;
    }

    // Handle outgoing response message flow of the WSO2 server
    public boolean handleResponseInFlow(MessageContext messageContext)
    {
        //log.info("Executing Response Inflow");
        return true;
    }

    public boolean handleResponseOutFlow(MessageContext messageContext)
    {
        //log.info("Executing Response Outflow");
        if(messageContext.getProperty("isInbound")!=null && messageContext.getProperty("isInbound").equals(true)){
            String uuid = "";
            //Fetching the uuid from the synapse Message Context
            if (messageContext.getProperty("WebActivityID")!=null){
                uuid = messageContext.getProperty("WebActivityID").toString();
            }else {
                log.warn("WebActivityID is not available in the response flow");
            }
            //Fetching the uuid from the axis2 Message Context
/*            Axis2MessageContext axis2smc = (Axis2MessageContext) messageContext;
            org.apache.axis2.context.MessageContext axis2MessageCtx = axis2smc.getAxis2MessageContext();
            if (axis2MessageCtx.getProperty("WebActivityID")!=null){
                uuid = axis2MessageCtx.getProperty("WebActivityID").toString();
            }else {
                log.warn("WebActivityID is not available in the response flow");
            }*/
            log.info("Response Outflow websocket.subscriber.path : " +
                    messageContext.getProperty("websocket.subscriber.path") +", WebActivityID uuid: " + uuid  );
        }
        return true;
    }
}