package com.alma.hadl.model.system.server.sql;

import com.alma.hadl.metamodel.connector.Connector;
import com.alma.hadl.metamodel.connector.Glue;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedRole;
import com.alma.hadl.metamodel.interfaces.required.RequiredRole;

import java.util.Arrays;

/**
 * Created by thomas on 24/10/16.
 */
public class SQLRequest extends Connector {
    public SQLRequest(RequiredRole<String> inQuery, ProvidedRole<String> outQuery,
                      RequiredRole<String> inQueryAnswer, ProvidedRole<String> outQueryAnswer) {
        super(Arrays.asList(inQuery, outQuery, inQueryAnswer, outQueryAnswer));
        Glue<String, String> gQuery = new GlueQuery();
        Glue<String, String> gQueryAnswer = new GlueQueryAnswer();
        addGlue(gQuery, inQuery, outQuery);
        addGlue(gQueryAnswer, inQueryAnswer, outQueryAnswer);
    }
}
