package com.sample;

import org.apache.synapse.Mediator;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

public class SequenceInvokerMediator extends AbstractMediator {

    private String sequenceToInvoke;

    public String getSequenceToInvoke() {
        return sequenceToInvoke;
    }

    public void setSequenceToInvoke(String sequenceToInvoke) {
        this.sequenceToInvoke = sequenceToInvoke;
    }

    @Override
    public boolean mediate(MessageContext messageContext) {

        // Get the sequence name from the property
        String sequenceName = getSequenceToInvoke();

        // Invoke the sequence
        if (sequenceName != null && !sequenceName.isEmpty()) {
            try {
                // Fetch the SequenceMediator object for the specified sequence name
                Mediator sequenceMediator = messageContext.getSequence(sequenceName);

                if (sequenceMediator != null) {
                    // Mediate the sequence
                    sequenceMediator.mediate(messageContext);
                } else {
                    log.error("Sequence not found: " + sequenceName);
                    // You can handle this case as needed
                }
            } catch (Exception e) {
                log.error("Error while invoking sequence: " + sequenceName, e);
                // You can handle the exception as needed
            }
        } else {
            log.error("Sequence name is not provided.");
            // You can handle this case as needed
        }

        return true;
    }
}