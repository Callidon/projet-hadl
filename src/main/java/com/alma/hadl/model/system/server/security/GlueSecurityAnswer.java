package com.alma.hadl.model.system.server.security;

import com.alma.hadl.metamodel.connector.Glue;

import java.util.logging.Logger;

/**
 * Glue qui gère les réponses aux requêtes de sécurité faites à une base de données.
 * @author Théo Couraud
 * @author Thomas Minier
 */
public class GlueSecurityAnswer implements Glue<String, String> {
    private static final Logger logger = Logger.getLogger(GlueSecurityAnswer.class.getName());
    @Override
    public String map(String input) {
        logger.info("[Glue Security Answer] map : " + input + " => " + input);
        return input;
    }
}
