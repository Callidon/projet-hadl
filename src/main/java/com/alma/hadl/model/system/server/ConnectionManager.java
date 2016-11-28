package com.alma.hadl.model.system.server;

import com.alma.hadl.metamodel.component.Component;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedPortComponent;
import com.alma.hadl.metamodel.interfaces.required.RequiredPortComponent;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

/**
 * Composant ConnnectionManager qui représente un gestionnaire de connexion, chargé de gérer les requêtes
 * selon un protocole précis. Il utilise d'autres composants de sa configuration à cette fin.
 * @author Théo Couraud
 * @author Thomas Minier
 */
public class ConnectionManager extends Component {
    private Queue<Properties> queries = new LinkedList<>();

    public ConnectionManager(ProvidedPortComponent<Properties> inputSocket, RequiredPortComponent<Properties> outputSocket,
                             ProvidedPortComponent<String> sendQuery, RequiredPortComponent<String> receiveQueryAnswer,
                             ProvidedPortComponent<String> sendAuthRequest, RequiredPortComponent<String> receiveAuthAnswer) {
        super("ConnectionManager", Arrays.asList(inputSocket, outputSocket, sendQuery, receiveQueryAnswer, sendAuthRequest, receiveAuthAnswer));

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
                // send an error to the client
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
