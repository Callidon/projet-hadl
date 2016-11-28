package com.alma.hadl.model.system.rpc;

import com.alma.hadl.metamodel.connector.Glue;

import java.util.Properties;
import java.util.logging.Logger;

/**
 * Glue qui gère les réponses aux requêtes faites à un serveur.
 * @author Théo Couraud
 * @author Thomas Minier
 */
public class GlueAnswer implements Glue<Properties, Properties> {
    private static final Logger logger = Logger.getLogger(GlueAnswer.class.getName());
    @Override
    public Properties map(Properties input) {
        logger.info("[Glue Answer] map : " + input + " => " + input);
        return input;
    }
}
