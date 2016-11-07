package com.alma.hadl.model.system;

import com.alma.hadl.metamodel.component.Component;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedPortComponent;
import com.alma.hadl.metamodel.interfaces.required.RequiredPortComponent;

import java.util.Arrays;
import java.util.Properties;

/**
 * Composant Server qui représente un serveur qui transfère la gestion des messages entrants
 * à une configuration ServerDetails, chargée de gérer ce comportement
 * @author Théo Couraud
 * @author Thomas Minier
 */
public class Server extends Component {
    public Server(RequiredPortComponent<Properties> receiveRequest, ProvidedPortComponent<Properties> sendAnswer,
                  ProvidedPortComponent<Properties> sendDetails, RequiredPortComponent<Properties> receiveDetails) {
        super("Server", Arrays.asList(receiveRequest, sendAnswer, sendDetails, receiveDetails));

        // Listen for incoming messages from Client
        receiveRequest.subscribe(sendDetails::send);

        // Listen for incoming messages from ServerDetails
        receiveDetails.subscribe(sendAnswer::send);
    }
}
