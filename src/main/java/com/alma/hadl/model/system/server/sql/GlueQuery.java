package com.alma.hadl.model.system.server.sql;

import com.alma.hadl.metamodel.connector.Glue;

import java.util.logging.Logger;

/**
 * Glue qui gère les requêtes SQL faites à une base de données.
 * @author Théo Couraud
 * @author Thomas Minier
 */
public class GlueQuery implements Glue<String, String> {
    private static final Logger logger = Logger.getLogger(GlueQuery.class.getName());
    @Override
    public String map(String input) {
        logger.info("[Glue Query] map : " + input + " => " + input);
        return input;
    }
}
