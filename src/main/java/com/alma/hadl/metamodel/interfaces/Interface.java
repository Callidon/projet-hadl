package com.alma.hadl.metamodel.interfaces;

import com.alma.hadl.metamodel.IObservable;
import com.alma.hadl.metamodel.IObserver;

import java.util.List;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public abstract class Interface<T> implements IObservable<T> {
    protected List<IObserver<T>> observers;

    public void addObserver(IObserver<T> obs) {
        observers.add(obs);
    }

    public void removeObserver(IObserver<T> obs) {
        observers.remove(obs);
    }

    public void notifyObservers(T data) {
        for(IObserver<T> obs : observers) {
            obs.update(data);
        }
    }

}
