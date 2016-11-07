package com.alma.hadl.model.system.server.clearence;

import com.alma.hadl.metamodel.connector.Glue;

/**
 * Created by thomas on 24/10/16.
 */
public class GlueAuthRequest implements Glue<String, byte[]> {
    @Override
    public byte[] map(String input) {
        return input.getBytes();
    }
}
