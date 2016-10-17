package com.alma.hadl.metamodel.component;

import com.alma.hadl.metamodel.Element;
import com.alma.hadl.metamodel.interfaces.Interface;

import java.util.List;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public abstract class Component extends Element {
    public Component(List<Interface> interfaces) {
        super(interfaces);
    }
}
