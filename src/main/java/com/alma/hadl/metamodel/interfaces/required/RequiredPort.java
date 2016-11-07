package com.alma.hadl.metamodel.interfaces.required;

/**
 * RequiredPort représente un port requis d'un composant ou d'une configuration.
 * @author Théo Couraud
 * @author Thomas Minier
 */
public class RequiredPort<T> extends Required<T> {

    /**
     * Constructeur
     * @param name Le nom du port requis
     */
    public RequiredPort(String name) {
        super(name);
    }
}
