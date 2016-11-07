package com.alma.hadl.metamodel.interfaces.provided;

/**
 * ProvidedPortComponent représente le port fourni d'une configuration.
 * @author Théo Couraud
 * @author Thomas Minier
 */
public class ProvidedPortConfiguration<T> extends Provided<T> {

    /**
     * Constructeur
     * @param name Le nom du port fourni
     */
    public ProvidedPortConfiguration(String name) {
        super(name);
    }
}
