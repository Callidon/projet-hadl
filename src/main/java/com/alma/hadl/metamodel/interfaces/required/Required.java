package com.alma.hadl.metamodel.interfaces.required;

import com.alma.hadl.metamodel.interfaces.Interface;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public abstract class Required<T> extends Interface<T> {
    public void receive(T data) {
        notifyObservers(data);
    }
}
