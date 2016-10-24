package com.alma.hadl.metamodel.connector;

import com.alma.hadl.metamodel.Element;
import com.alma.hadl.metamodel.IObserver;
import com.alma.hadl.metamodel.interfaces.Interface;
import com.alma.hadl.metamodel.interfaces.provided.Provided;
import com.alma.hadl.metamodel.interfaces.required.Required;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public abstract class Connector extends Element {
    protected List<Glue> glues;

    public Connector(List<Interface> interfaces) {
        super(interfaces);
        glues = new ArrayList<>();
    }

    public <I,O> void addGlue(final Glue<I,O> glue, Required<I> input, final Provided<O> output) {
        glues.add(glue);
        input.subscribe(new IObserver<I>() {
            public void update(I data) {
                output.send(glue.map(data));
            }
        });
    }

}
