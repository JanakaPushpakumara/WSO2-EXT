package com.sample;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.synapse.MessageContext;
import org.apache.synapse.core.SynapseEnvironment;
import org.apache.synapse.endpoints.Endpoint;
import org.apache.synapse.mediators.AbstractMediator;
import org.apache.synapse.message.senders.blocking.BlockingMsgSender;

import java.nio.file.Paths;

/**
 * This is a sample class mediator  that log for message flows.
 */

public class TestMediator extends AbstractMediator {


    public final static String DEFAULT_AXIS2_XML;
    static {
        String confPath = System.getProperty("conf.location");
        if (confPath == null) {
            confPath = System.getProperty("carbon.config.dir.path");
            if (confPath == null) {
                confPath = Paths.get("repository", "conf").toString();
            }
        }
        DEFAULT_AXIS2_XML = Paths.get(confPath, "axis2", "axis2_blocking_client.xml").toString();
    }

    private BlockingMsgSender blockingMsgSender = null;

    private ConfigurationContext configCtx = null;

    private Endpoint endpoint = null;

    private boolean blocking = false;

    private SynapseEnvironment synapseEnv;

    private boolean initClientOptions = true;

    private String clientRepository = null;

    private String axis2xml = null;
    public final static String DEFAULT_CLIENT_REPO = "./repository/deployment/client";
    private static final Log log = LogFactory.getLog(TestMediator.class);

    public boolean mediate(MessageContext messageContext) {
        log.info("Class mediator invoked");

        try {
            configCtx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(
                    clientRepository != null ? clientRepository : DEFAULT_CLIENT_REPO,
                    axis2xml != null ? axis2xml : DEFAULT_AXIS2_XML);
        } catch (AxisFault e) {
            throw new RuntimeException(e);
        }

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