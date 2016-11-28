package com.alma.hadl.model.system.server.sql;

import com.alma.hadl.metamodel.connector.Glue;

import java.util.logging.Logger;

/**
 * Glue qui gère les réponses aux requêtes SQL faites à une base de données.
 * @author Théo Couraud
 * @author Thomas Minier
 */
public class GlueQueryAnswer implements Glue<String, String> {
    private static final Logger logger = Logger.getLogger(GlueQueryAnswer.class.getName());
    @Override
    public String map(String input) {
        logger.info("[Glue Query Answer] map : " + input + " => " + input);
        return input;
    }
}
