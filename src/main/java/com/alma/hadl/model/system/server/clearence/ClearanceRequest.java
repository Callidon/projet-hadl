package com.alma.hadl.model.system.server.clearence;

import com.alma.hadl.metamodel.connector.Connector;
import com.alma.hadl.metamodel.connector.Glue;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedRole;
import com.alma.hadl.metamodel.interfaces.required.RequiredRole;
import com.alma.hadl.model.system.server.sql.GlueQueryAnswer;

import java.util.Arrays;

/**
 * Created by thomas on 24/10/16.
 */
public class ClearanceRequest extends Connector {
    public ClearanceRequest(RequiredRole<String> inAuthRequest, ProvidedRole<byte[]> outAuthRequest,
                            RequiredRole<String> inAuthAnswer, ProvidedRole<String> outAuthAnswer) {
        super("ClearanceRequest", Arrays.asList(inAuthRequest, outAuthRequest, inAuthAnswer, outAuthAnswer));
        Glue<String, byte[]> gAuthRequest = new GlueAuthRequest();
        Glue<String, String> gAuthAnswer = new GlueQueryAnswer();
        addGlue(gAuthRequest, inAuthRequest, outAuthRequest);
        addGlue(gAuthAnswer, inAuthAnswer, outAuthAnswer);
    }
}
