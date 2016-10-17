package com.alma.hadl.metamodel;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public interface IObserver<T> {
    void update(T data);
}
