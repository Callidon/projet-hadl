package com.alma.hadl.model.system.client;

import com.alma.hadl.metamodel.component.Component;
import java.util.Arrays;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public class Client extends Component {
    public Client(SendRequest input, ReceiveAnswer output) {
        super(Arrays.asList(input, output));
    }
}
