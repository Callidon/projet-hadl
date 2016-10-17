package com.alma.hadl.model.system.client;

import com.alma.hadl.metamodel.interfaces.provided.ProvidedPort;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public class SendRequest extends ProvidedPort<String> {
    public SendRequest() {
        super("Send request");
    }

    @Override
    public void send(String data) {
        super.send(data);
    }
}
