package com.alma.hadl.metamodel.interfaces.required;

/**
 * RequiredRole représente un rôle requis d'un connecteur.
 * @author Théo Couraud
 * @author Thomas Minier
 */
public class RequiredRole<T> extends Required<T> {

    /**
     * Constructeur
     * @param name Le nom du rôle requis
     */
    public RequiredRole(String name) {
        super(name);
    }
}
