package com.alma.hadl.model.system.server;

import com.alma.hadl.metamodel.component.Component;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedPort;
import com.alma.hadl.metamodel.interfaces.required.RequiredPort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by thomas on 24/10/16.
 */
public class ConnectionManager extends Component {
    private Queue<String> messages = new LinkedList<>();

    public ConnectionManager(ProvidedPort<String> inputSocket, RequiredPort<String> outputSocket,
                             ProvidedPort<String> sendQuery, RequiredPort<String> receiveQueryAnswer,
                             ProvidedPort<String> sendAuthRequest, RequiredPort<String> receiveAuthAnswer) {
        super(Arrays.asList(inputSocket, outputSocket, sendQuery, receiveQueryAnswer, sendAuthRequest, receiveAuthAnswer));

        // Listen for incoming messages
        inputSocket.subscribe(data -> {
            // store message and ask for security authorization
            messages.add(data);
            sendAuthRequest.send("id;password");
        });

        // Listen for incoming Authorization answers
        receiveAuthAnswer.subscribe(data -> {
            if("ok".equals(data)) {
                String query = messages.poll();
                sendQuery.send(query);
            } else {
                outputSocket.receive(data);
            }
        });

        // Listen for incoming SQL query answers
        receiveQueryAnswer.subscribe(data -> {
            outputSocket.receive(data);
        });
    }
}
