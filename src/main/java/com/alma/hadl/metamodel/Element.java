package com.alma.hadl.metamodel;

import com.alma.hadl.metamodel.interfaces.Interface;

import java.util.List;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public abstract class Element {
    protected List<Interface> interfaces;

    public Element(List<Interface> interfaces) {
        this.interfaces = interfaces;
    }
}
