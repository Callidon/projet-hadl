package com.alma.hadl.metamodel.interfaces;

import com.alma.hadl.metamodel.IObservable;
import com.alma.hadl.metamodel.IObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public abstract class Interface<T> implements IObservable<T> {
    private String name;
    protected List<IObserver<T>> observers;
    protected final Logger LOGGER = Logger.getLogger(this.getClass().getCanonicalName());

    public Interface(String name) {
        this.name = name;
        observers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

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
