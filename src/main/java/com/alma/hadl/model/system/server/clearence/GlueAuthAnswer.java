package com.alma.hadl.model.system.server.clearence;

import com.alma.hadl.metamodel.connector.Glue;

import java.util.logging.Logger;

/**
 * Glue qui gère les réponses aux requêtes d'authentification faites à un gestionnaire de sécurité.
 * @author Théo Couraud
 * @author Thomas Minier
 */
public class GlueAuthAnswer implements Glue<String, String> {
    private static final Logger logger = Logger.getLogger(GlueAuthAnswer.class.getName());

    @Override
    public String map(String input) {
        logger.info("[Glue Auth Answer] map : " + input + " => " + input);
        return input;
    }
}
