package com.alma.hadl.model.system.server.security;

import com.alma.hadl.metamodel.connector.Glue;

import java.util.logging.Logger;

/**
 * Glue qui gère les requêtes de sécurités faites à une base de données.
 * @author Théo Couraud
 * @author Thomas Minier
 */
public class GlueSecurityRequest implements Glue<String, String> {
    private static final Logger logger = Logger.getLogger(GlueSecurityRequest.class.getName());
    @Override
    public String map(String input) {
        logger.info("[Glue Security Request] map : " + input + " => " + input);
        return input;
    }
}
