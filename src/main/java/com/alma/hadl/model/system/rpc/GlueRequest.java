package com.alma.hadl.model.system.rpc;

import com.alma.hadl.metamodel.connector.Glue;

import java.util.Properties;
import java.util.logging.Logger;

/**
 * Glue qui gère les requêtes entrantes faites à un serveur.
 * @author Théo Couraud
 * @author Thomas Minier
 */
public class GlueRequest implements Glue<Properties, Properties> {
    private static final Logger logger = Logger.getLogger(GlueRequest.class.getName());
    @Override
    public Properties map(Properties input) {
        logger.info("[Glue Request] map : " + input + " => " + input);
        return input;
    }
}
