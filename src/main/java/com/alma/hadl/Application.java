package com.alma.hadl;

import com.alma.hadl.metamodel.interfaces.provided.ProvidedPort;
import com.alma.hadl.metamodel.interfaces.required.RequiredPort;
import com.alma.hadl.model.system.SystemApplication;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public class Application {

    public static void main(String[] args) {
        ProvidedPort<String> inputClient = new ProvidedPort<>("Input Client");
        RequiredPort<String> outputClient = new RequiredPort<>("Output Client");
        SystemApplication system = new SystemApplication(inputClient, outputClient);
        outputClient.addObserver(System.out::println);
        inputClient.send("Toto");
    }
}
