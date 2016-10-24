package com.alma.hadl.model.system.server;

import com.alma.hadl.metamodel.component.Component;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedPort;
import com.alma.hadl.metamodel.interfaces.required.RequiredPort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

/**
 * Created by thomas on 24/10/16.
 */
public class ConnectionManager extends Component {

    public ConnectionManager(ProvidedPort<Properties> inputSocket, RequiredPort<Properties> outputSocket,
                             ProvidedPort<String> sendQuery, RequiredPort<String> receiveQueryAnswer,
                             ProvidedPort<String> sendAuthRequest, RequiredPort<String> receiveAuthAnswer) {
        super(Arrays.asList(inputSocket, outputSocket, sendQuery, receiveQueryAnswer, sendAuthRequest, receiveAuthAnswer));

        // Listen for incoming messages
        inputSocket.subscribe(data -> {
            // forward message to the appropriate component
            String type = data.getProperty("type");
            switch (type) {
                case "auth-request":
                    sendAuthRequest.send(data.getProperty("username") + ";" + data.getProperty("mdp"));
                    break;
                case "sql-query":
                    sendQuery.send(data.getProperty("query"));
                    break;
                default:
                    Properties response = new Properties();
                    response.setProperty("type", "error-message");
                    response.setProperty("value", "unknown message type");
                    outputSocket.receive(response);
            }
        });

        // Listen for incoming Authorization answers
        receiveAuthAnswer.subscribe(data -> {
            Properties response = new Properties();
            response.setProperty("type", "auth-answer");
            response.setProperty("value", data);
            outputSocket.receive(response);
        });

        // Listen for incoming SQL query answers
        receiveQueryAnswer.subscribe(data -> {
            Properties response = new Properties();
            response.setProperty("type", "sql-answer");
            response.setProperty("value", data);
            outputSocket.receive(response);
        });
    }
}
