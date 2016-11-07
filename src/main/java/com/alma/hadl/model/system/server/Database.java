package com.alma.hadl.model.system.server;

import com.alma.hadl.metamodel.component.Component;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedPortComponent;
import com.alma.hadl.metamodel.interfaces.required.RequiredPortComponent;

import java.util.*;

/**
 * Composant Database qui représente une base de données SQL.
 * En l'état, le comportement d'une vraie base est imitée par une HashMap, pour simplifier l'implémentation.
 * @author Théo Couraud
 * @author Thomas Minier
 */
public class Database extends Component {
    private Map<String, String> datas = new HashMap<>();

    public Database(ProvidedPortComponent<String> sendQueryAnswer, RequiredPortComponent<String> receiveQuery,
                    ProvidedPortComponent<String> sendSecurityAnswer, RequiredPortComponent<String> receiveSecurityRequest) {
        super(Arrays.asList(sendQueryAnswer, receiveQuery, sendSecurityAnswer, receiveSecurityRequest));

        // Mock the content of a database
        datas.put("momo", "Des nains et des hommes");
        datas.put("toto", "Toto a deux amis : Pierre et Philippe");

        // Listen for incoming SQL queries
        receiveQuery.subscribe(data -> {
            String response = datas.get(data);
            if(response == null) {
                sendQueryAnswer.send("No data corresponding to the query : " + data);
            } else {
                sendQueryAnswer.send(response);
            }
        });

        // Listen for incoming security request
        receiveSecurityRequest.subscribe(data -> {
            if(!datas.isEmpty()) {
                sendSecurityAnswer.send("ok");
            } else {
                sendSecurityAnswer.send("database unavailable");
            }
        });
    }
}
