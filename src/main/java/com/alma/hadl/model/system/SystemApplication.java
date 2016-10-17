package com.alma.hadl.model.system;

import com.alma.hadl.metamodel.IObserver;
import com.alma.hadl.metamodel.configuration.Configuration;
import com.alma.hadl.metamodel.interfaces.Interface;
import com.alma.hadl.model.system.client.Client;
import com.alma.hadl.model.system.client.ReceiveAnswer;
import com.alma.hadl.model.system.client.SendRequest;
import com.alma.hadl.model.system.rpc.InRequest;
import com.alma.hadl.model.system.rpc.OutRequest;
import com.alma.hadl.model.system.rpc.RPC;

import java.util.Collections;
import java.util.List;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public class SystemApplication extends Configuration {

    public SendRequest sendRequest;
    public OutRequest outRequest;

    public SystemApplication(List<Interface> interfaces) {
        super(interfaces);
        // creates the components of the configuration
        //SendRequest sendRequest = new SendRequest();
        sendRequest = new SendRequest();
        ReceiveAnswer receiveAnswer = new ReceiveAnswer();
        Client client = new Client(sendRequest, receiveAnswer);

        InRequest inRequest = new InRequest();
        //OutRequest outRequest = new OutRequest();
        outRequest = new OutRequest();
        RPC rpc = new RPC(inRequest, outRequest);

        // ...

        // calls to attach & bind
        attach(sendRequest, inRequest);
        attach(outRequest, receiveAnswer); // TODO : remove, only here for debug
        // ...


        // add the elements to the config
        addElement(client);
        addElement(rpc);
    }

    public static void main(String[] args) {
        SystemApplication system = new SystemApplication(Collections.emptyList());
        system.outRequest.addObserver(System.out::println);
        system.sendRequest.send("Toto");

    }
}
