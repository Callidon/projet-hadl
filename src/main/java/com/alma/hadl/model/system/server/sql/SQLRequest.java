package com.alma.hadl.model.system.server.sql;

import com.alma.hadl.metamodel.connector.Connector;
import com.alma.hadl.metamodel.connector.Glue;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedRole;
import com.alma.hadl.metamodel.interfaces.required.RequiredRole;

import java.util.Arrays;

/**
 * Connecteur SQL Request qui représente une connexion entre un gestionnaire de connexion et une base de données.
 * @author Théo Couraud
 * @author Thomas Minier
 */
public class SQLRequest extends Connector {
    public SQLRequest(RequiredRole<String> inQuery, ProvidedRole<String> outQuery,
                      RequiredRole<String> inQueryAnswer, ProvidedRole<String> outQueryAnswer) {
        super("SQLRequest", Arrays.asList(inQuery, outQuery, inQueryAnswer, outQueryAnswer));
        Glue<String, String> gQuery = new GlueQuery();
        Glue<String, String> gQueryAnswer = new GlueQueryAnswer();
        addGlue(gQuery, inQuery, outQuery);
        addGlue(gQueryAnswer, inQueryAnswer, outQueryAnswer);
    }
}
