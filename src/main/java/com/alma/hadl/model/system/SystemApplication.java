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
import java.util.Properties;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public class SystemApplication extends Configuration {

    public SystemApplication(ProvidedPort<Properties> inputClient, RequiredPort<Properties> outputClient) {
        super(Arrays.asList(inputClient, outputClient));
        // creates the components of the configuration

        // Creates component Client
        ProvidedPort<Properties> sendRequest = new ProvidedPort<>("Send Request");
        RequiredPort<Properties> receiveAnswer = new RequiredPort<>("Receive Answer");
        Component client = new Client(sendRequest, receiveAnswer);

        // Creates component Server
        RequiredPort<Properties> receiveRequest = new RequiredPort<>("Receive Request");
        ProvidedPort<Properties> sendAnswer = new ProvidedPort<>("Send Answer");
        ProvidedPort<Properties> sendDetails = new ProvidedPort<>("Send Details");
        RequiredPort<Properties> receiveDetails = new RequiredPort<>("Receive Details");
        Component server = new Server(receiveRequest, sendAnswer, sendDetails, receiveDetails);

        // Creates connector RPC
        RequiredRole<Properties> inRequest = new RequiredRole<>("In Request");
        ProvidedRole<Properties> outRequest = new ProvidedRole<>("Out Request");
        RequiredRole<Properties> inAnswer = new RequiredRole<>("In Answer");
        ProvidedRole<Properties> outAnswer = new ProvidedRole<>("Out Answer");
        Connector rpc = new RPC(inRequest, outRequest, inAnswer, outAnswer);

        // Creates configuration ServerDetails
        ProvidedPort<Properties> inputDetails = new ProvidedPort<>("Input Details");
        RequiredPort<Properties> outputDetails = new RequiredPort<>("Output Details");
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
