package com.sample;

import org.apache.commons.codec.binary.Base64;
import org.apache.synapse.MessageContext;
import org.apache.synapse.SynapseException;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.core.axis2.Axis2Sender;
import org.apache.synapse.rest.Handler;
import org.wso2.micro.integrator.security.MicroIntegratorSecurityUtils;
import org.wso2.micro.integrator.security.user.api.UserStoreException;
import org.wso2.micro.integrator.security.user.api.UserStoreManager;


import java.util.Map;

public class RoleBasedAuthorization implements Handler {

    private String[] allowedRoles;

    public boolean handleRequest(MessageContext messageContext) {

        org.apache.axis2.context.MessageContext axis2MessageContext
                = ((Axis2MessageContext) messageContext).getAxis2MessageContext();
        Object headers = axis2MessageContext.getProperty(
                org.apache.axis2.context.MessageContext.TRANSPORT_HEADERS);

        if (headers instanceof Map) {
            Map headersMap = (Map) headers;
            if (headersMap.get("Authorization") == null) {
                headersMap.clear();
                axis2MessageContext.setProperty("HTTP_SC", "401");
                axis2MessageContext.setProperty("NO_ENTITY_BODY", new Boolean("true"));
                messageContext.setProperty("RESPONSE", "true");
                messageContext.setTo(null);
                Axis2Sender.sendBack(messageContext);
                return false;

            } else {
                String authHeader = (String) headersMap.get("Authorization");
                try {
                    if (processSecurity(authHeader)) {
                        return true;
                    } else {
                        headersMap.clear();
                        axis2MessageContext.setProperty("HTTP_SC", "403");
                        axis2MessageContext.setProperty("NO_ENTITY_BODY", new Boolean("true"));
                        messageContext.setProperty("RESPONSE", "true");
                        messageContext.setTo(null);
                        Axis2Sender.sendBack(messageContext);
                        return false;
                    }
                } catch (UserStoreException e) {
                    throw new SynapseException(e);
                }
            }
        }
        return false;
    }

    public boolean handleResponse(MessageContext messageContext) {
        return true;
    }

    public void addProperty(String s, Object o) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Map getProperties() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean processSecurity(String authHeader) throws UserStoreException {
        if (authHeader != null && authHeader.startsWith("Basic ")) {
            String encodedCredentials = authHeader.substring("Basic ".length()).trim();
            byte[] decodedBytes = Base64.decodeBase64(encodedCredentials.getBytes());
            String credentials = new String(decodedBytes);

            // Now you can access the username and password from the credentials string
            String[] usernameAndPassword = credentials.split(":");
            String username = usernameAndPassword[0];
            String password = usernameAndPassword[1];
            UserStoreManager userStoreManager = MicroIntegratorSecurityUtils.getUserStoreManager();
            if (isUserauthenticated(userStoreManager, username, password)){
                if (isUserAutherized(userStoreManager, username, allowedRoles)){
                    return true;
                }
            }

        }
        return false;
    }

    private boolean isUserauthenticated(UserStoreManager userStoreManager, String usernName, String password) throws UserStoreException {

        if (userStoreManager.authenticate(usernName, password)) {
            return true;
        }
        return false;
    }

    private boolean isUserAutherized(UserStoreManager userStoreManager, String username, String[] allowedRoles) throws UserStoreException {
        String[] userRoles = userStoreManager.getRoleListOfUser(username);
        for (String allowedrole : allowedRoles ){
            for (String userRole : userRoles){
                if (allowedrole.trim().equals(userRole.trim()))
                    return true;
            }
        }
        return false;
    }

    public String[] getAllowedRoles() {
        return allowedRoles;
    }

    public void setAllowedRoles(String allowedRoles) {
        this.allowedRoles = allowedRoles.split(",");;
    }
}
