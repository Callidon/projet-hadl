package com.alma.hadl.model.system.rpc;

import com.alma.hadl.metamodel.connector.Glue;

import java.util.Properties;

/**
 * Created by thomas on 24/10/16.
 */
public class GlueAnswer implements Glue<Properties, Properties> {
    @Override
    public Properties map(Properties input) {
        return input;
    }
}
