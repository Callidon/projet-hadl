package com.alma.hadl.model.system.server;

import com.alma.hadl.metamodel.component.Component;
import com.alma.hadl.metamodel.configuration.Configuration;
import com.alma.hadl.metamodel.connector.Connector;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedPort;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedRole;
import com.alma.hadl.metamodel.interfaces.required.RequiredPort;
import com.alma.hadl.metamodel.interfaces.required.RequiredRole;
import com.alma.hadl.model.system.server.clearence.ClearenceRequest;
import com.alma.hadl.model.system.server.security.SecurityRequest;
import com.alma.hadl.model.system.server.sql.SQLRequest;

import java.util.Arrays;

/**
 * Created by thomas on 24/10/16.
 */
public class ServerDetails extends Configuration {
    public ServerDetails(ProvidedPort<String> inputDetails, RequiredPort<String> outputDetails) {
        super(Arrays.asList(inputDetails, outputDetails));

        // Creates component ConnectionManager
        ProvidedPort<String> inputSocket = new ProvidedPort<>("Input Socket");
        RequiredPort<String> outputSocket = new RequiredPort<>("Output Socket");
        ProvidedPort<String> sendQuery = new ProvidedPort<>("Send Query");
        RequiredPort<String> receiveQueryAnswer = new RequiredPort<>("Receive Query Answer");
        ProvidedPort<String> sendAuthRequest = new ProvidedPort<>("Send Auth Request");
        RequiredPort<String> receiveAuthAnswer = new RequiredPort<>("Receive Auth Answer");
        Component connectionManager = new ConnectionManager(inputSocket, outputSocket,
                sendQuery, receiveQueryAnswer, sendAuthRequest, receiveAuthAnswer);

        // Creates component Database
        ProvidedPort<String> sendQueryAnswer = new ProvidedPort<>("Send Query Answer");
        RequiredPort<String> receiveQuery = new RequiredPort<>("Receive Query");
        ProvidedPort<String> sendSecurityRequest = new ProvidedPort<>("Send Security Request");
        RequiredPort<String> receiveSecurityAnswer = new RequiredPort<>("Receive Security Answer");
        Component database = new Database(sendQueryAnswer, receiveQuery, sendSecurityRequest, receiveSecurityAnswer);

        // Creates component SecurityManager
        ProvidedPort<String> sendAuthAnswer = new ProvidedPort<>("Send Auth Answer");
        RequiredPort<String> receiveAuthRequest = new RequiredPort<>("Receive Auth Request");
        ProvidedPort<String> sendSecurityAnswer = new ProvidedPort<>("Send Security Answer");
        RequiredPort<String> receiveSecurityRequest = new RequiredPort<>("Receive Security Request");
        Component securityManager = new SecurityManager(sendAuthAnswer, receiveAuthRequest, sendSecurityAnswer, receiveSecurityRequest);

        // Creates connector SQLRequest
        RequiredRole<String> inQuery = new RequiredRole<>("In Query");
        ProvidedRole<String> outQuery = new ProvidedRole<>("Out Query");
        RequiredRole<String> inQueryAnswer = new RequiredRole<>("In Query Answer");
        ProvidedRole<String> outQueryAnswer = new ProvidedRole<>("Out Query Answer");
        Connector sqlRequest = new SQLRequest(inQuery, outQuery, inQueryAnswer, outQueryAnswer);

        // Creates connector ClearenceRequest
        RequiredRole<String> inAuthRequest = new RequiredRole<>("In Auth Request");
        ProvidedRole<String> outAuthRequest = new ProvidedRole<>("Out Auth Request");
        RequiredRole<String> inAuthAnswer = new RequiredRole<>("In Auth Answer");
        ProvidedRole<String> outAuthAnswer = new ProvidedRole<>("Out Auth Answer");
        Connector clearenceRequest = new ClearenceRequest(inAuthRequest, outAuthRequest, inAuthAnswer, outAuthAnswer);

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
        attach(outAuthAnswer, receiveAuthRequest);
        // securityManager#sendAuthAnswer -> clearenceRequest#inAuthAnswer
        attach(sendAuthAnswer, inAuthAnswer);
        // clearenceRequest#outAuthAnswer -> connectionManager#receiveAuthAnswer
        attach(outAuthAnswer, receiveAuthAnswer);

        // database#sendSecurityRequest -> securityRequest#inSecurityRequest
        attach(sendSecurityRequest, inSecurityRequest);
        // securityRequest#outSecurityRequest -> securityManager#receiveSecurityRequest
        attach(outSecurityRequest, receiveSecurityRequest);
        // securityManager#sendSecurityAnswer -> securityRequest#inSecurityAnswer
        attach(sendSecurityAnswer, inSecurityAnswer);
        // securityRequest#outSecurityAnswer -> database#receiveSecurityAnswer
        attach(outSecurityAnswer, receiveSecurityAnswer);

        // add elements to the configuration
        addElement(connectionManager);
        addElement(database);
        addElement(securityManager);
        addElement(sqlRequest);
        addElement(clearenceRequest);
        addElement(securityRequest);
    }
}
