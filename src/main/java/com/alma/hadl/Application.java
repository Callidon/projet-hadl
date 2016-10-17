package com.alma.hadl;

import com.alma.hadl.model.system.InputClient;
import com.alma.hadl.model.system.OutputClient;
import com.alma.hadl.model.system.SystemApplication;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public class Application {

    public static void main(String[] args) {
        InputClient inputClient = new InputClient();
        OutputClient outputClient = new OutputClient();
        SystemApplication system = new SystemApplication(inputClient, outputClient);
        outputClient.addObserver(System.out::println);
        inputClient.send("Toto");
    }
}
