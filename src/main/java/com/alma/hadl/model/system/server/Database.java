package com.alma.hadl.model.system.server;

import com.alma.hadl.metamodel.component.Component;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedPortComponent;
import com.alma.hadl.metamodel.interfaces.required.RequiredPortComponent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
        super("Database", Arrays.asList(sendQueryAnswer, receiveQuery, sendSecurityAnswer, receiveSecurityRequest));

        // Mock the content of a database
        datas.put("tata", "Oh ! Je crois que j'ai vu un gros minet !");
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
            // check if the database has records.
            // It can also be a more complex verification of the state of the database.
            if(!datas.isEmpty()) {
                sendSecurityAnswer.send("ok");
            } else {
                sendSecurityAnswer.send("database unavailable");
            }
        });
    }
}
