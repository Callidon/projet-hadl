package com.alma.hadl.metamodel.interfaces.provided;

/**
 * ProvidedRole représente le rôle fourni d'un connecteur.
 * @author Théo Couraud
 * @author Thomas Minier
 */
public class ProvidedRole<T> extends Provided<T> {

    /**
     * Constructeur
     * @param name Le nom du rôle fourni
     */
    public ProvidedRole(String name) {
        super(name);
    }
}
