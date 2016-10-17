package com.alma.hadl.model.system.rpc;

import com.alma.hadl.metamodel.connector.Connector;
import com.alma.hadl.metamodel.connector.Glue;

import java.util.Arrays;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public class RPC extends Connector {
    public RPC(InRequest ir, OutRequest or) {
        super(Arrays.asList(ir, or));
        Glue<String, String> gRequest = new GlueRequest();
        addGlue(gRequest, ir, or);
    }
}
