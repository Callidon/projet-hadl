package com.alma.hadl.metamodel;

/**
 * IObservable représente l'interface d'un objet qui peut être observer par des observateurs
 * @author Théo Couraud
 * @author Thomas Minier
 */
public interface IObservable<T> {
    void subscribe(IObserver<T> obs);
    void unsubscribe(IObserver<T> obs);
    void unsubscribeAll();
    void notifyObservers(T data);
    void transferSubscriptions(IObservable<T> target);
}
