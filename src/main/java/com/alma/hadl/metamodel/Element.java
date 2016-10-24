package com.alma.hadl.metamodel;

import com.alma.hadl.metamodel.interfaces.Interface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public abstract class Element {
    protected Map<String, Interface> interfaces = new HashMap<>();

    public Element(List<Interface> interfaces) {
        for(Interface interf : interfaces) {
            this.interfaces.put(interf.getName(), interf);
        }
    }

    public Interface getInterface(String name) {
        return interfaces.get(name);
    }
}
