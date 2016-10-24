package com.alma.hadl.model.system;

import com.alma.hadl.metamodel.configuration.Configuration;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedPort;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedRole;
import com.alma.hadl.metamodel.interfaces.required.RequiredPort;
import com.alma.hadl.metamodel.interfaces.required.RequiredRole;
import com.alma.hadl.model.system.rpc.RPC;

import java.util.Arrays;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public class SystemApplication extends Configuration {

    public SystemApplication(ProvidedPort<String> inputClient, RequiredPort<String> outputClient) {
        super(Arrays.asList(inputClient, outputClient));
        // creates the components of the configuration
        ProvidedPort<String> sendRequest = new ProvidedPort<>("Send Request");
        RequiredPort<String> receiveAnswer = new RequiredPort<>("Receive Answer");
        Client client = new Client(sendRequest, receiveAnswer);

        RequiredPort<String> receiveRequest = new RequiredPort<>("Receive Request");
        ProvidedPort<String> sendAnswer = new ProvidedPort<>("Send Answer");
        Server server = new Server(receiveRequest, sendAnswer);

        RequiredRole<String> inRequest = new RequiredRole<>("In Request");
        ProvidedRole<String> outRequest = new ProvidedRole<>("Out Request");
        RequiredRole<String> inAnswer = new RequiredRole<>("In Answer");
        ProvidedRole<String> outAnswer = new ProvidedRole<>("Out Answer");
        RPC rpc = new RPC(inRequest, outRequest, inAnswer, outAnswer);

        // create bindings
        bind(inputClient, sendRequest);
        bind(receiveAnswer, outputClient);
        // create attachments
        attach(sendRequest, inRequest);
        attach(outRequest, receiveRequest);
        attach(sendAnswer, inAnswer);
        attach(outAnswer, receiveAnswer);

        // add the elements to the config
        addElement(client);
        addElement(server);
        addElement(rpc);
    }
}
