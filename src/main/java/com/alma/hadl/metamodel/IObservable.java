package com.alma.hadl.metamodel;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public interface IObservable<T> {
    void addObserver(IObserver<T> obs);
    void removeObserver(IObserver<T> obs);
    void notifyObservers(T data);
}
