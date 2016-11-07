package com.alma.hadl;

import com.alma.hadl.metamodel.IObserver;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedPort;
import com.alma.hadl.metamodel.interfaces.required.RequiredPort;
import com.alma.hadl.model.system.SystemApplication;

import java.util.Properties;

/**
 * Classe principale de l'application
 * @author Théo Couraud
 * @author Thomas Minier
 */
public class Application {

    /**
     * Méthode principale
     */
    public static void main(String[] args) {
        ProvidedPort<Properties> inputClient = new ProvidedPort<>("Input Client");
        RequiredPort<Properties> outputClient = new RequiredPort<>("Output Client");
        SystemApplication system = new SystemApplication(inputClient, outputClient);

        outputClient.subscribe(System.out::println);

        Properties query = new Properties();
        query.setProperty("username", "thomas");
        query.setProperty("mdp", "my-great-password");
        query.setProperty("query", "toto");
        inputClient.send(query);
    }
}
