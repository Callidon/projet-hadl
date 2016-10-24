package com.alma.hadl.model.system.rpc;

import com.alma.hadl.metamodel.connector.Glue;

import java.util.Properties;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public class GlueRequest implements Glue<Properties, Properties> {
    @Override
    public Properties map(Properties input) {
        return input;
    }
}
