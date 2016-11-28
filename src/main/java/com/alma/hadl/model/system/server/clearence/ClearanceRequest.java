package com.alma.hadl.model.system.server.clearence;

import com.alma.hadl.metamodel.connector.Connector;
import com.alma.hadl.metamodel.connector.Glue;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedRole;
import com.alma.hadl.metamodel.interfaces.required.RequiredRole;

import java.util.Arrays;

/**
 * Connecteur Clearence Request qui représente une connexion entre un gestionnaire de connexion et un gestionnaire de sécurité.
 * @author Théo Couraud
 * @author Thomas Minier
 */
public class ClearanceRequest extends Connector {
    public ClearanceRequest(RequiredRole<String> inAuthRequest, ProvidedRole<byte[]> outAuthRequest,
                            RequiredRole<String> inAuthAnswer, ProvidedRole<String> outAuthAnswer) {
        super("ClearanceRequest", Arrays.asList(inAuthRequest, outAuthRequest, inAuthAnswer, outAuthAnswer));
        Glue<String, byte[]> gAuthRequest = new GlueAuthRequest();
        Glue<String, String> gAuthAnswer = new GlueAuthAnswer();
        addGlue(gAuthRequest, inAuthRequest, outAuthRequest);
        addGlue(gAuthAnswer, inAuthAnswer, outAuthAnswer);
    }
}
