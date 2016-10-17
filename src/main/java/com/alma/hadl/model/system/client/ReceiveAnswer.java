package com.alma.hadl.model.system.client;

import com.alma.hadl.metamodel.interfaces.required.RequiredPort;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public class ReceiveAnswer extends RequiredPort<String> {
    public ReceiveAnswer() {
        super("Receive Answer");
    }

    @Override
    public void receive(String data) {
        super.receive(data);
    }
}
