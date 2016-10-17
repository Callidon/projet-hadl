package com.alma.hadl.model.system.rpc;

import com.alma.hadl.metamodel.interfaces.required.RequiredRole;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public class InRequest extends RequiredRole<String> {
    public InRequest() {
        super("In Request");
    }

    @Override
    public void receive(String data) {
        super.receive(data);
    }
}
