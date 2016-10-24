package com.alma.hadl.model.system.rpc;

import com.alma.hadl.metamodel.connector.Glue;

/**
 * Created by thomas on 24/10/16.
 */
public class GlueAnswer implements Glue<String, String> {
    @Override
    public String map(String input) {
        return input;
    }
}
