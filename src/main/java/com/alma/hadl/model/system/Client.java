package com.alma.hadl.model.system;

import com.alma.hadl.metamodel.component.Component;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedPortComponent;
import com.alma.hadl.metamodel.interfaces.required.RequiredPortComponent;

import java.util.Arrays;
import java.util.Properties;

/**
 * Composant Client qui représente un client qui transmet des requêtes à un serveur
 * @author Théo Couraud
 * @author Thomas Minier
 */
public class Client extends Component {
    public Client(ProvidedPortComponent<Properties> input, RequiredPortComponent<Properties> output) {
        super(Arrays.asList(input, output));
    }
}
