package com.alma.hadl.model.system.rpc;

import com.alma.hadl.metamodel.connector.Connector;
import com.alma.hadl.metamodel.connector.Glue;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedRole;
import com.alma.hadl.metamodel.interfaces.required.RequiredRole;

import java.util.Arrays;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public class RPC extends Connector {
    public RPC(RequiredRole<String> inRequest, ProvidedRole<String> outRequest, RequiredRole<String> inAnswer, ProvidedRole<String> outAnswer) {
        super(Arrays.asList(inRequest, outRequest, inAnswer, outAnswer));
        Glue<String, String> gRequest = new GlueRequest();
        Glue<String, String> gAnswer = new GlueAnswer();
        addGlue(gRequest, inRequest, outRequest);
        addGlue(gAnswer, inAnswer, outAnswer);
    }
}
