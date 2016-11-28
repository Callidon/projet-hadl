package com.alma.hadl.metamodel.interfaces.required;

import com.alma.hadl.metamodel.interfaces.Interface;

/**
 * Required représente une interface requise.
 * Elle reçoit des données et les transfèrent à ses observateurs.
 * @author Théo Couraud
 * @author Thomas Minier
 */
public abstract class Required<T> extends Interface<T> {

    /**
     * Constructeur
     * @param name Le nom de l'interface requise
     */
    public Required(String name) {
        super(name);
    }

    /**
     * Méthode qui envoie les données à l'interface requise.
     * Par défaut, elle sera transmise à ses observateurs.
     * @param data La donnée reçue par l'interface requise.
     */
    public void receive(T data) {
        logger.info("[Required Interface] '" + getName() + "' - received data : " + data.toString());
        notifyObservers(data);
    }
}
