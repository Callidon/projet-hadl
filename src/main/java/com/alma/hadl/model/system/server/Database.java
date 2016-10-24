package com.alma.hadl.model.system.server;

import com.alma.hadl.metamodel.component.Component;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedPort;
import com.alma.hadl.metamodel.interfaces.required.RequiredPort;

import java.util.*;

/**
 * Created by thomas on 24/10/16.
 */
public class Database extends Component {
    private Queue<String> queries = new LinkedList<>();
    private Map<String, String> datas = new HashMap<>();

    public Database(ProvidedPort<String> sendQueryAnswer, RequiredPort<String> receiveQuery,
                    ProvidedPort<String> sendSecurityRequest, RequiredPort<String> receiveSecurityAnswer) {
        super(Arrays.asList(sendQueryAnswer, receiveQuery, sendSecurityRequest, receiveSecurityAnswer));

        // Mock the content of a database
        datas.put("momo", "Des nains et des hommes");
        datas.put("toto", "Toto a deux amis : Pierre et Philippe");

        // Listen for incoming SQL queries
        receiveQuery.subscribe(data -> {
            queries.add(data);
            // ask for security infos
            sendSecurityRequest.send("need data");
        });

        // Listen for incoming security request answers
        receiveSecurityAnswer.subscribe(data -> {
            if("ok".equals(data)) {
                String query = queries.poll();
                String response = datas.get(query);
                if(response == null) {
                    sendQueryAnswer.send("No data corresponding to the query : " + query);
                } else {
                    sendQueryAnswer.send(response);
                }
            } else {
                sendQueryAnswer.send("Invalid query, permissions denied");
            }
        });
    }
}
