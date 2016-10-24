package com.alma.hadl.model.system;

import com.alma.hadl.metamodel.component.Component;
import com.alma.hadl.metamodel.interfaces.provided.Provided;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedPort;
import com.alma.hadl.metamodel.interfaces.required.RequiredPort;

import java.util.Arrays;
import java.util.Properties;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public class Client extends Component {
    public Client(ProvidedPort<Properties> input, RequiredPort<Properties> output) {
        super(Arrays.asList(input, output));
    }
}
