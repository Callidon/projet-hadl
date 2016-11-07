package com.alma.hadl.metamodel.interfaces.required;

/**
 * RequiredPortComponent représente un port requis d'un composant
 * @author Théo Couraud
 * @author Thomas Minier
 */
public class RequiredPortComponent<T> extends Required<T> {

    /**
     * Constructeur
     * @param name Le nom du port requis
     */
    public RequiredPortComponent(String name) {
        super(name);
    }
}
