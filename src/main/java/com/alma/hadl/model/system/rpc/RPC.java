package com.alma.hadl.model.system.rpc;

import com.alma.hadl.metamodel.connector.Connector;
import com.alma.hadl.metamodel.connector.Glue;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedRole;
import com.alma.hadl.metamodel.interfaces.required.RequiredRole;

import java.util.Arrays;
import java.util.Properties;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public class RPC extends Connector {
    public RPC(RequiredRole<Properties> inRequest, ProvidedRole<Properties> outRequest, RequiredRole<Properties> inAnswer, ProvidedRole<Properties> outAnswer) {
        super(Arrays.asList(inRequest, outRequest, inAnswer, outAnswer));
        Glue<Properties, Properties> gRequest = new GlueRequest();
        Glue<Properties, Properties> gAnswer = new GlueAnswer();
        addGlue(gRequest, inRequest, outRequest);
        addGlue(gAnswer, inAnswer, outAnswer);
    }
}
