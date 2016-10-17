package com.alma.hadl.model.system;

import com.alma.hadl.metamodel.interfaces.required.RequiredPort;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public class OutputClient extends RequiredPort<String> {
    public OutputClient() {
        super("Output client");
    }

    @Override
    public void receive(String data) {
        super.receive(data);
    }
}
