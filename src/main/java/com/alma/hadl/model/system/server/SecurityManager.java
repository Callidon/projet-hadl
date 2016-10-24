package com.alma.hadl.model.system.server;

import com.alma.hadl.metamodel.component.Component;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedPort;
import com.alma.hadl.metamodel.interfaces.required.RequiredPort;

import java.util.Arrays;

/**
 * Created by thomas on 24/10/16.
 */
public class SecurityManager extends Component {
    public SecurityManager(ProvidedPort<String> sendAuthAnswer, RequiredPort<String> receiveAuthRequest,
                           ProvidedPort<String> sendSecurityAnswer, RequiredPort<String> receiveSecurityRequest) {
        super(Arrays.asList(sendAuthAnswer, receiveAuthRequest, sendSecurityAnswer, receiveSecurityRequest));

        // Listen for incoming Authorization requests
        receiveAuthRequest.subscribe(data -> {
            if("id;password".equals(data)) {
                sendAuthAnswer.send("ok");
            } else {
                sendAuthAnswer.send("access denied, incorrect security query");
            }
        });

        // Listen for incoming security requests
        receiveSecurityRequest.subscribe(data -> {
            sendSecurityAnswer.send("ok");
        });
    }
}
