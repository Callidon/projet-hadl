package com.alma.hadl.metamodel.connector;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public interface Glue<I,O> {
    O map(I input);
}
