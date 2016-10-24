package com.alma.hadl.model.system.server;

import com.alma.hadl.metamodel.component.Component;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedPort;
import com.alma.hadl.metamodel.interfaces.required.RequiredPort;

import java.util.Arrays;

/**
 * Created by thomas on 24/10/16.
 */
public class ConnectionManager extends Component {
    public ConnectionManager(ProvidedPort<String> inputSocket, RequiredPort<String> outputSocket,
                             ProvidedPort<String> sendQuery, RequiredPort<String> receiveQueryAnswer,
                             ProvidedPort<String> sendAuthRequest, RequiredPort<String> receiveAuthAnswer) {
        super(Arrays.asList(inputSocket, outputSocket, sendQuery, receiveQueryAnswer, sendAuthRequest, receiveAuthAnswer));
    }
}
