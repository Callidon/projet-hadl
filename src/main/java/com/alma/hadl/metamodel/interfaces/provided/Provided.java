package com.alma.hadl.metamodel.interfaces.provided;

import com.alma.hadl.metamodel.interfaces.Interface;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public abstract class Provided<T> extends Interface<T> {

    public Provided(String name) {
        super(name);
    }

    public void send(T data) {
        notifyObservers(data);
    }
}
