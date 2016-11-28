package com.alma.hadl.metamodel;

/**
 * IObserver représente l'interface d'un objet qui peut observer d'autres objets.
 * @author Théo Couraud
 * @author Thomas Minier
 */
@FunctionalInterface
public interface IObserver<T> {
    void update(T data);
}
