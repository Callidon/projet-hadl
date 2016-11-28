package com.alma.hadl.model.system.server.security;

import com.alma.hadl.metamodel.connector.Connector;
import com.alma.hadl.metamodel.connector.Glue;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedRole;
import com.alma.hadl.metamodel.interfaces.required.RequiredRole;

import java.util.Arrays;

/**
 * Connecteur Security Request qui représente une connexion entre un gestionnaire de sécurité et une base de données.
 * @author Théo Couraud
 * @author Thomas Minier
 */
public class SecurityRequest extends Connector {
    public SecurityRequest(RequiredRole<String> inSecurityRequest, ProvidedRole<String> outSecurityRequest,
                           RequiredRole<String> inSecurityAnswer, ProvidedRole<String> outSecurityAnswer) {
        super("SecurityRequest", Arrays.asList(inSecurityRequest, outSecurityRequest, inSecurityAnswer, outSecurityAnswer));
        Glue<String, String> gSecurityRequest = new GlueSecurityRequest();
        Glue<String, String> gSecurityAnswer = new GlueSecurityAnswer();
        addGlue(gSecurityRequest, inSecurityRequest, outSecurityRequest);
        addGlue(gSecurityAnswer, inSecurityAnswer, outSecurityAnswer);
    }
}
