package com.alma.hadl.model.system;

import com.alma.hadl.metamodel.component.Component;
import com.alma.hadl.metamodel.configuration.Configuration;
import com.alma.hadl.metamodel.connector.Connector;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedPort;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedRole;
import com.alma.hadl.metamodel.interfaces.required.RequiredPort;
import com.alma.hadl.metamodel.interfaces.required.RequiredRole;
import com.alma.hadl.model.system.rpc.RPC;
import com.alma.hadl.model.system.server.ServerDetails;

import java.util.Arrays;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public class SystemApplication extends Configuration {

    public SystemApplication(ProvidedPort<String> inputClient, RequiredPort<String> outputClient) {
        super(Arrays.asList(inputClient, outputClient));
        // creates the components of the configuration

        // Creates component Client
        ProvidedPort<String> sendRequest = new ProvidedPort<>("Send Request");
        RequiredPort<String> receiveAnswer = new RequiredPort<>("Receive Answer");
        Component client = new Client(sendRequest, receiveAnswer);

        // Creates component Server
        RequiredPort<String> receiveRequest = new RequiredPort<>("Receive Request");
        ProvidedPort<String> sendAnswer = new ProvidedPort<>("Send Answer");
        ProvidedPort<String> sendDetails = new ProvidedPort<>("Send Details");
        RequiredPort<String> receiveDetails = new RequiredPort<>("Receive Details");
        Component server = new Server(receiveRequest, sendAnswer, sendDetails, receiveDetails);

        // Creates connector RPC
        RequiredRole<String> inRequest = new RequiredRole<>("In Request");
        ProvidedRole<String> outRequest = new ProvidedRole<>("Out Request");
        RequiredRole<String> inAnswer = new RequiredRole<>("In Answer");
        ProvidedRole<String> outAnswer = new ProvidedRole<>("Out Answer");
        Connector rpc = new RPC(inRequest, outRequest, inAnswer, outAnswer);

        // Creates configuration ServerDetails
        ProvidedPort<String> inputDetails = new ProvidedPort<>("Input Details");
        RequiredPort<String> outputDetails = new RequiredPort<>("Output Details");
        Configuration serverDetails = new ServerDetails(inputDetails, outputDetails);

        // creates bindings
        bind(inputClient, sendRequest);
        bind(receiveAnswer, outputClient);
        bind(sendDetails, inputDetails);
        bind(outputDetails, receiveDetails);

        // creates attachments
        // client#sendRequest -> rpc#inRequest
        attach(sendRequest, inRequest);
        // rpc#outRequest -> server#receiveRequest
        attach(outRequest, receiveRequest);
        // server#sendAnswer -> rpc#inAnswer
        attach(sendAnswer, inAnswer);
        // rpc#outAnswer -> client#receiveAnswer
        attach(outAnswer, receiveAnswer);

        // add the elements to the config
        addElement(client);
        addElement(server);
        addElement(rpc);
        addElement(serverDetails);
    }
}
