package com.alma.hadl.metamodel.interfaces.required;

/**
 * RequiredPortComponent représente un port requis d'une configuration.
 * @author Théo Couraud
 * @author Thomas Minier
 */
public class RequiredPortConfiguration<T> extends Required<T> {

    /**
     * Constructeur
     * @param name Le nom du port requis
     */
    public RequiredPortConfiguration(String name) {
        super(name);
    }
}
