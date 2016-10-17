package com.alma.hadl.model.system.rpc;

import com.alma.hadl.metamodel.interfaces.provided.ProvidedRole;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public class OutRequest extends ProvidedRole<String> {
    public OutRequest() {
        super("Out Request");
    }

    @Override
    public void send(String data) {
        super.send(data);
    }
}
