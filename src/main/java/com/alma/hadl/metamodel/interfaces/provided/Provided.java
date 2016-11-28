package com.alma.hadl.metamodel.interfaces.provided;

import com.alma.hadl.metamodel.interfaces.Interface;

/**
 * Provided représente une interface fournie.
 * Par défaut, elle transmet des données à ses observateurs.
 * @author Théo Couraud
 * @author Thomas Minier
 */
public abstract class Provided<T> extends Interface<T> {

    /**
     * Constructeur
     * @param name Le nom de l'interface fournie
     */
    public Provided(String name) {
        super(name);
    }

    /**
     * Méthode qui envoie les données depuis l'interface fournie.
     * Par défaut, elles sont transmisent aux observateurs de l'interface fournie.
     * @param data La donnée à envoyer.
     */
    public void send(T data) {
        logger.info("[Provided Interface] '" + getName() + "' - send data : " + data.toString());
        notifyObservers(data);
    }
}
