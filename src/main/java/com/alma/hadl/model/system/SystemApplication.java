package com.alma.hadl.model.system;

import com.alma.hadl.metamodel.component.Component;
import com.alma.hadl.metamodel.configuration.Configuration;
import com.alma.hadl.metamodel.connector.Connector;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedPortComponent;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedPortConfiguration;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedRole;
import com.alma.hadl.metamodel.interfaces.required.RequiredPortComponent;
import com.alma.hadl.metamodel.interfaces.required.RequiredPortConfiguration;
import com.alma.hadl.metamodel.interfaces.required.RequiredRole;
import com.alma.hadl.model.system.rpc.RPC;
import com.alma.hadl.model.system.server.ServerDetails;

import java.util.Arrays;
import java.util.Properties;

/**
 * Configuration qui contient l'application Client Serveur
 * @author Théo Couraud
 * @author Thomas Minier
 */
public class SystemApplication extends Configuration {

    public SystemApplication(ProvidedPortConfiguration<Properties> inputClient, RequiredPortConfiguration<Properties> outputClient) {
        super(Arrays.asList(inputClient, outputClient));
        // creates the components of the configuration

        // Creates component Client
        ProvidedPortComponent<Properties> sendRequest = new ProvidedPortComponent<>("Send Request");
        RequiredPortComponent<Properties> receiveAnswer = new RequiredPortComponent<>("Receive Answer");
        Component client = new Client(sendRequest, receiveAnswer);

        // Creates component Server
        RequiredPortComponent<Properties> receiveRequest = new RequiredPortComponent<>("Receive Request");
        ProvidedPortComponent<Properties> sendAnswer = new ProvidedPortComponent<>("Send Answer");
        ProvidedPortComponent<Properties> sendDetails = new ProvidedPortComponent<>("Send Details");
        RequiredPortComponent<Properties> receiveDetails = new RequiredPortComponent<>("Receive Details");
        Component server = new Server(receiveRequest, sendAnswer, sendDetails, receiveDetails);

        // Creates connector RPC
        RequiredRole<Properties> inRequest = new RequiredRole<>("In Request");
        ProvidedRole<Properties> outRequest = new ProvidedRole<>("Out Request");
        RequiredRole<Properties> inAnswer = new RequiredRole<>("In Answer");
        ProvidedRole<Properties> outAnswer = new ProvidedRole<>("Out Answer");
        Connector rpc = new RPC(inRequest, outRequest, inAnswer, outAnswer);

        // Creates configuration ServerDetails
        ProvidedPortConfiguration<Properties> inputDetails = new ProvidedPortConfiguration<>("Input Details");
        RequiredPortConfiguration<Properties> outputDetails = new RequiredPortConfiguration<>("Output Details");
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
