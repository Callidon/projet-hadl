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
    private Queue<Properties> queries = new LinkedList<>();

    public ConnectionManager(ProvidedPort<Properties> inputSocket, RequiredPort<Properties> outputSocket,
                             ProvidedPort<String> sendQuery, RequiredPort<String> receiveQueryAnswer,
                             ProvidedPort<String> sendAuthRequest, RequiredPort<String> receiveAuthAnswer) {
        super(Arrays.asList(inputSocket, outputSocket, sendQuery, receiveQueryAnswer, sendAuthRequest, receiveAuthAnswer));

        // Listen for incoming messages
        inputSocket.subscribe(data -> {
            // store query & validate username using Security manager
            queries.add(data);
            sendAuthRequest.send(data.getProperty("mdp"));
        });

        // Listen for incoming Authorization answers
        receiveAuthAnswer.subscribe(data -> {
            if("ok".equals(data)) {
                // get the query & transfer it to the database
                Properties query = queries.poll();
                sendQuery.send(query.getProperty("query"));
            } else {
                // send a error response to the client
                Properties response = new Properties();
                response.setProperty("error-value", data);
                outputSocket.receive(response);
            }
        });

        // Listen for incoming SQL query answers
        receiveQueryAnswer.subscribe(data -> {
            // send the result of the query to the client
            Properties response = new Properties();
            response.setProperty("type", "sql-answer");
            response.setProperty("value", data);
            outputSocket.receive(response);
        });
    }
}
