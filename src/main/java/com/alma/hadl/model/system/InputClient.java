package com.alma.hadl.model.system;

import com.alma.hadl.metamodel.interfaces.provided.ProvidedPort;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public class InputClient extends ProvidedPort<String> {
    public InputClient() {
        super("Input client");
    }

    @Override
    public void send(String data) {
        super.send(data);
    }
}
