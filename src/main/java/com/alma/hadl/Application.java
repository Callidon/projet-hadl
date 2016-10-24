package com.alma.hadl;

import com.alma.hadl.metamodel.interfaces.provided.ProvidedPort;
import com.alma.hadl.metamodel.interfaces.required.RequiredPort;
import com.alma.hadl.model.system.SystemApplication;

import java.util.Properties;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public class Application {

    public static void main(String[] args) {
        ProvidedPort<Properties> inputClient = new ProvidedPort<>("Input Client");
        RequiredPort<Properties> outputClient = new RequiredPort<>("Output Client");
        SystemApplication system = new SystemApplication(inputClient, outputClient);

        outputClient.subscribe(data -> {
            String type = data.getProperty("type");
            switch (type) {
                case "auth-answer":
                    String response = data.getProperty("value");
                    if("ok".equals(response)) {
                        Properties sqlQuery = new Properties();
                        sqlQuery.setProperty("type", "sql-query");
                        sqlQuery.setProperty("query", "toto");
                        inputClient.send(sqlQuery);
                    } else {
                        System.out.println(response);
                    }
                    break;
                case "sql-answer":
                    System.out.println(data.getProperty("value"));
                    break;
                default:
                    System.out.println("unkown answer type : " + type);
                    break;
            }
        });

        Properties query = new Properties();
        query.setProperty("type", "auth-request");
        query.setProperty("username", "thomas");
        query.setProperty("mdp", "my-shiny-password");

        inputClient.send(query);
    }
}
