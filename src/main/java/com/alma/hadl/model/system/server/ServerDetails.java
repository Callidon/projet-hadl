package com.alma.hadl.model.system.server;

import com.alma.hadl.metamodel.component.Component;
import com.alma.hadl.metamodel.configuration.Configuration;
import com.alma.hadl.metamodel.connector.Connector;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedPortComponent;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedPortConfiguration;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedRole;
import com.alma.hadl.metamodel.interfaces.required.RequiredPortComponent;
import com.alma.hadl.metamodel.interfaces.required.RequiredPortConfiguration;
import com.alma.hadl.metamodel.interfaces.required.RequiredRole;
import com.alma.hadl.model.system.server.clearence.ClearanceRequest;
import com.alma.hadl.model.system.server.security.SecurityRequest;
import com.alma.hadl.model.system.server.sql.SQLRequest;

import java.util.Arrays;
import java.util.Properties;

/**
 * Configuration ServerDetails qui représente une configuration qui gère les requêtes à une base de données.
 * Une requête entrante est prise en charge par un gestionnaire de connexion, qui coordonne son exécution.
 * Elle passe d'abord par une phase de validation avec un gestionnaire de sécurité, qui vérifie le mot de passe fourni
 * et si l'état de la base permet que la requête y soit exécutée. Elle est ensuite transférée à la base de données elle-même pour y être exécutée.
 * Les résultats et les erreurs éventuels sont remontés via le port requis de la configuration.
 * @author Théo Couraud
 * @author Thomas Minier
 */
public class ServerDetails extends Configuration {
    public ServerDetails(ProvidedPortConfiguration<Properties> inputDetails, RequiredPortConfiguration<Properties> outputDetails) {
        super("ServerDetails", Arrays.asList(inputDetails, outputDetails));

        // Creates component ConnectionManager
        ProvidedPortComponent<Properties> inputSocket = new ProvidedPortComponent<>("Input Socket");
        RequiredPortComponent<Properties> outputSocket = new RequiredPortComponent<>("Output Socket");
        ProvidedPortComponent<String> sendQuery = new ProvidedPortComponent<>("Send Query");
        RequiredPortComponent<String> receiveQueryAnswer = new RequiredPortComponent<>("Receive Query Answer");
        ProvidedPortComponent<String> sendAuthRequest = new ProvidedPortComponent<>("Send Auth Request");
        RequiredPortComponent<String> receiveAuthAnswer = new RequiredPortComponent<>("Receive Auth Answer");
        Component connectionManager = new ConnectionManager(inputSocket, outputSocket,
                sendQuery, receiveQueryAnswer, sendAuthRequest, receiveAuthAnswer);

        // Creates component Database
        ProvidedPortComponent<String> sendQueryAnswer = new ProvidedPortComponent<>("Send Query Answer");
        RequiredPortComponent<String> receiveQuery = new RequiredPortComponent<>("Receive Query");
        ProvidedPortComponent<String> sendSecurityAnswer = new ProvidedPortComponent<>("Send Security Answer");
        RequiredPortComponent<String> receiveSecurityRequest = new RequiredPortComponent<>("Receive Security Request");
        Component database = new Database(sendQueryAnswer, receiveQuery, sendSecurityAnswer, receiveSecurityRequest);

        // Creates component SecurityManager
        ProvidedPortComponent<String> sendAuthAnswer = new ProvidedPortComponent<>("Send Auth Answer");
        RequiredPortComponent<byte[]> receiveAuthRequest = new RequiredPortComponent<>("Receive Auth Request");
        ProvidedPortComponent<String> sendSecurityRequest = new ProvidedPortComponent<>("Send Security Request");
        RequiredPortComponent<String> receiveSecurityAnswer = new RequiredPortComponent<>("Receive Security Answer");
        Component securityManager = new SecurityManager(sendAuthAnswer, receiveAuthRequest, sendSecurityRequest, receiveSecurityAnswer);

        // Creates connector SQLRequest
        RequiredRole<String> inQuery = new RequiredRole<>("In Query");
        ProvidedRole<String> outQuery = new ProvidedRole<>("Out Query");
        RequiredRole<String> inQueryAnswer = new RequiredRole<>("In Query Answer");
        ProvidedRole<String> outQueryAnswer = new ProvidedRole<>("Out Query Answer");
        Connector sqlRequest = new SQLRequest(inQuery, outQuery, inQueryAnswer, outQueryAnswer);

        // Creates connector ClearanceRequest
        RequiredRole<String> inAuthRequest = new RequiredRole<>("In Auth Request");
        ProvidedRole<byte[]> outAuthRequest = new ProvidedRole<>("Out Auth Request");
        RequiredRole<String> inAuthAnswer = new RequiredRole<>("In Auth Answer");
        ProvidedRole<String> outAuthAnswer = new ProvidedRole<>("Out Auth Answer");
        Connector clearanceRequest = new ClearanceRequest(inAuthRequest, outAuthRequest, inAuthAnswer, outAuthAnswer);

        // Creates connector SecurityRequest
        RequiredRole<String> inSecurityRequest = new RequiredRole<>("In Security Request");
        ProvidedRole<String> outSecurityRequest = new ProvidedRole<>("Out Security Request");
        RequiredRole<String> inSecurityAnswer = new RequiredRole<>("In Security Answer");
        ProvidedRole<String> outSecurityAnswer = new ProvidedRole<>("Out Security Answer");
        Connector securityRequest = new SecurityRequest(inSecurityRequest, outSecurityRequest, inSecurityAnswer, outSecurityAnswer);

        // creates bindings
        bind(inputDetails, inputSocket);
        bind(outputSocket, outputDetails);

        // creates attachments

        // connectionManager#sendQuery -> sqlRequest#inQuery
        attach(sendQuery, inQuery);

        // sqlRequest#outQuery -> database#receiveQuery
        attach(outQuery, receiveQuery);

        // database#sendQueryAnswer -> sqlRequest#inQueryAnswer
        attach(sendQueryAnswer, inQueryAnswer);

        // sqlRequest#outQueryAnswer -> connectionManager#receiveQueryAnswer
        attach(outQueryAnswer, receiveQueryAnswer);

        // connectionManager#sendAuthRequest -> clearenceRequest#inAuthRequest
        attach(sendAuthRequest, inAuthRequest);

        // clearenceRequest#outAuthAnswer -> securityManager#receiveAuthRequest
        attach(outAuthRequest, receiveAuthRequest);

        // securityManager#sendAuthAnswer -> clearenceRequest#inAuthAnswer
        attach(sendAuthAnswer, inAuthAnswer);

        // clearenceRequest#outAuthAnswer -> connectionManager#receiveAuthAnswer
        attach(outAuthAnswer, receiveAuthAnswer);

        // securityManager#sendSecurityRequest -> securityRequest#inSecurityRequest
        attach(sendSecurityRequest, inSecurityRequest);

        // securityRequest#outSecurityRequest -> database#receiveSecurityRequest
        attach(outSecurityRequest, receiveSecurityRequest);

        // database#sendSecurityAnswer -> securityRequest#inSecurityAnswer
        attach(sendSecurityAnswer, inSecurityAnswer);

        // securityRequest#outSecurityAnswer -> securityManager#receiveSecurityAnswer
        attach(outSecurityAnswer, receiveSecurityAnswer);

        // add elements to the configuration
        addElement(connectionManager);
        addElement(database);
        addElement(securityManager);
        addElement(sqlRequest);
        addElement(clearanceRequest);
        addElement(securityRequest);
    }
}
