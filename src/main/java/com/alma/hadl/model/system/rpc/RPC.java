package com.alma.hadl.model.system.rpc;

import com.alma.hadl.metamodel.connector.Connector;
import com.alma.hadl.metamodel.connector.Glue;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedRole;
import com.alma.hadl.metamodel.interfaces.required.RequiredRole;

import java.util.Arrays;
import java.util.Properties;

/**
 * Composant RPC qui représente une connexion entre un serveur et un client.
 * @author Théo Couraud
 * @author Thomas Minier
 */
public class RPC extends Connector {
    public RPC(RequiredRole<Properties> inRequest, ProvidedRole<Properties> outRequest, RequiredRole<Properties> inAnswer, ProvidedRole<Properties> outAnswer) {
        super("RPC", Arrays.asList(inRequest, outRequest, inAnswer, outAnswer));
        Glue<Properties, Properties> gRequest = new GlueRequest();
        Glue<Properties, Properties> gAnswer = new GlueAnswer();
        addGlue(gRequest, inRequest, outRequest);
        addGlue(gAnswer, inAnswer, outAnswer);
    }
}
