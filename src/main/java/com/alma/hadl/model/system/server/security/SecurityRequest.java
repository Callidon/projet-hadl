package com.alma.hadl.model.system.server.security;

import com.alma.hadl.metamodel.connector.Connector;
import com.alma.hadl.metamodel.connector.Glue;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedRole;
import com.alma.hadl.metamodel.interfaces.required.RequiredRole;

import java.util.Arrays;

/**
 * Created by thomas on 24/10/16.
 */
public class SecurityRequest extends Connector {
    public SecurityRequest(RequiredRole<String> inSecurityRequest, ProvidedRole<String> outSecurityRequest,
                           RequiredRole<String> inSecurityAnswer, ProvidedRole<String> outSecurityAnswer) {
        super(Arrays.asList(inSecurityRequest, outSecurityRequest, inSecurityAnswer, outSecurityAnswer));
        Glue<String, String> gSecurityRequest = new GlueSecurityAnswer();
        Glue<String, String> gSecurityAnswer = new GlueSecurityAnswer();
        addGlue(gSecurityRequest, inSecurityRequest, outSecurityRequest);
        addGlue(gSecurityAnswer, inSecurityAnswer, outSecurityAnswer);
    }
}
