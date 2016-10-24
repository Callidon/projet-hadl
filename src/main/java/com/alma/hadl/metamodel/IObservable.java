package com.alma.hadl.metamodel;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public interface IObservable<T> {
    void subscribe(IObserver<T> obs);
    void unsubscribe(IObserver<T> obs);
    void notifyObservers(T data);
}
