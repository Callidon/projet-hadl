package com.alma.hadl.model.system.rpc;

import com.alma.hadl.metamodel.connector.Glue;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public class GlueRequest implements Glue<String, String> {
    @Override
    public String map(String input) {
        return input + ", qu'on se le dise !";
    }
}
