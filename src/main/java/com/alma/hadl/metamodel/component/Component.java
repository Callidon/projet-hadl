package com.alma.hadl.metamodel.component;

import com.alma.hadl.metamodel.Element;
import com.alma.hadl.metamodel.interfaces.Interface;

import java.util.List;

/**
 * Component représente un composant du modèle d'Architecture orientée composant.
 * Il possède des interfaces, qui sont des ports requis ou fournis
 * @author Théo Couraud
 * @author Thomas Minier
 */
public abstract class Component extends Element {

    /**
     * Constructeur
     * @param interfaces Les interfaces que possède le composant
     */
    public Component(String name, List<Interface> interfaces) {
        super(name, interfaces);
    }
}
