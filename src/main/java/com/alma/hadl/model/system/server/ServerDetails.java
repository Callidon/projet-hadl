package com.alma.hadl.model.system.server;

import com.alma.hadl.metamodel.configuration.Configuration;
import com.alma.hadl.metamodel.interfaces.Interface;

import java.util.List;

/**
 * Created by thomas on 24/10/16.
 */
public class ServerDetails extends Configuration {
    public ServerDetails(List<Interface> interfaces) {
        super(interfaces);
    }
}
