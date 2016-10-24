package com.alma.hadl.model.system;

import com.alma.hadl.metamodel.IObserver;
import com.alma.hadl.metamodel.component.Component;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedPort;
import com.alma.hadl.metamodel.interfaces.required.RequiredPort;

import java.util.Arrays;

/**
 * Created by thomas on 24/10/16.
 */
public class Server extends Component {
    public Server(RequiredPort<String> receiveRequest, ProvidedPort<String> sendAnswer) {
        super(Arrays.asList(receiveRequest, sendAnswer));

        receiveRequest.addObserver(new IObserver<String>() {
            @Override
            public void update(String data) {
                System.out.println("server received data : " + data);
                sendAnswer.send("received data");
            }
        });
    }
}
