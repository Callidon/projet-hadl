package com.alma.hadl.metamodel.interfaces.provided;

/**
 * ProvidedPort représente le port fourni d'un composant ou d'une configuration.
 * @author Théo Couraud
 * @author Thomas Minier
 */
public class ProvidedPort<T> extends Provided<T> {

    /**
     * Constructeur
     * @param name Le nom du port fourni
     */
    public ProvidedPort(String name) {
        super(name);
    }
}
