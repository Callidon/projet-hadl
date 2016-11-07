package com.alma.hadl.metamodel.interfaces.provided;

/**
 * ProvidedPortComponent représente le port fourni d'un composant
 * @author Théo Couraud
 * @author Thomas Minier
 */
public class ProvidedPortComponent<T> extends Provided<T> {

    /**
     * Constructeur
     * @param name Le nom du port fourni
     */
    public ProvidedPortComponent(String name) {
        super(name);
    }
}
