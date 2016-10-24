package com.alma.hadl.model.system;

import com.alma.hadl.metamodel.component.Component;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedPort;
import com.alma.hadl.metamodel.interfaces.required.RequiredPort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by thomas on 24/10/16.
 */
public class Server extends Component {
    private List<Properties> messages = new ArrayList<>();
    public Server(RequiredPort<Properties> receiveRequest, ProvidedPort<Properties> sendAnswer,
                  ProvidedPort<Properties> sendDetails, RequiredPort<Properties> receiveDetails) {
        super(Arrays.asList(receiveRequest, sendAnswer, sendDetails, receiveDetails));

        // Listen for incoming messages from Client
        receiveRequest.subscribe(data -> {
            messages.add(data);
            sendDetails.send(data);
        });

        // Listen for incoming messages from ServerDetails
        receiveDetails.subscribe(response -> {
            sendAnswer.send(response);
        });
    }
}
