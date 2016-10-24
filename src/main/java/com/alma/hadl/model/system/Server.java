package com.alma.hadl.model.system;

import com.alma.hadl.metamodel.component.Component;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedPort;
import com.alma.hadl.metamodel.interfaces.required.RequiredPort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by thomas on 24/10/16.
 */
public class Server extends Component {
    private List<String> messages = new ArrayList<>();
    public Server(RequiredPort<String> receiveRequest, ProvidedPort<String> sendAnswer,
                  ProvidedPort<String> sendDetails, RequiredPort<String> receiveDetails) {
        super(Arrays.asList(receiveRequest, sendAnswer, sendDetails, receiveDetails));

        // Listen for incoming messages from Client
        receiveRequest.subscribe(data -> {
            messages.add(data);
            //forwardRequest(data, sendDetails);
            sendDetails.send(data);
        });

        // Listen for incoming messages from ServerDetails
        receiveDetails.subscribe(response -> {
            sendAnswer.send(response);
        });
    }

    private void forwardRequest(String message, ProvidedPort<String> sendDetails) {
        sendDetails.send(message);
    }
}
