package com.alma.hadl.model.system.server.security;

import com.alma.hadl.metamodel.connector.Glue;

/**
 * Created by thomas on 24/10/16.
 */
public class GlueSecurityRequest implements Glue<String, String> {
    @Override
    public String map(String input) {
        return input;
    }
}
