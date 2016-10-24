package com.alma.hadl.model.system.server;

import com.alma.hadl.metamodel.component.Component;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedPort;
import com.alma.hadl.metamodel.interfaces.required.RequiredPort;

import java.util.Arrays;

/**
 * Created by thomas on 24/10/16.
 */
public class Database extends Component {
    public Database(ProvidedPort<String> sendQueryAnswer, RequiredPort<String> receiveQuery,
                    ProvidedPort<String> sendSecurityRequest, RequiredPort<String> receiveSecurityAnswer) {
        super(Arrays.asList(sendQueryAnswer, receiveQuery, sendSecurityRequest, receiveSecurityAnswer));
    }
}
