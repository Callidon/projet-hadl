package com.alma.hadl.metamodel.interfaces.required;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public abstract class RequiredRole<T> extends Required<T> {
    public RequiredRole(String name) {
        super(name);
    }
}
