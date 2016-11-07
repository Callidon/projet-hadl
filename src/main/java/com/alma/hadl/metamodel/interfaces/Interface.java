package com.alma.hadl.metamodel.interfaces;

import com.alma.hadl.metamodel.IObservable;
import com.alma.hadl.metamodel.IObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Interface représente une interface abstraite qu'un élément possède.
 * Une interface peut être observés par d'autres objets. Les liens d'attachments et bindings seront modélisés ainsi.
 * @author Théo Couraud
 * @author Thomas Minier
 */
public abstract class Interface<T> implements IObservable<T> {
    /**
     * Le nom de l'interface
     */
    private String name;

    /**
     * La liste des observateurs qui observent cette interface.
     */
    protected List<IObserver<T>> observers;

    /**
     * Le Logger dédié de la classe. Il sera utilisé pour afficher la trace des données dans l'application
     */
    protected final Logger LOGGER = Logger.getLogger(this.getClass().getCanonicalName());

    /**
     * Constructeur
     * @param name Le nom de l'interface
     */
    public Interface(String name) {
        this.name = name;
        observers = new ArrayList<>();
    }

    /**
     * Accesseur sur le nom de l'interface
     * @return Le nom de l'interface
     */
    public String getName() {
        return name;
    }

    /**
     * Méthode permettant d'abonner un observateur à cette interface
     * @param obs L'observeur qui veut observer l'interface
     */
    @Override
    public void subscribe(IObserver<T> obs) {
        observers.add(obs);
    }

    /**
     * Méthode permettant de désabonner un observateur à cette interface
     * @param obs
     */
    @Override
    public void unsubscribe(IObserver<T> obs) {
        observers.remove(obs);
    }

    /**
     * Méthode qui nootifient les observateurs d'un changement au niveau de l'interface.
     * @param data La donnée qu'il faut transmettre aux observateurs
     */
    @Override
    public void notifyObservers(T data) {
        for(IObserver<T> obs : observers) {
            obs.update(data);
        }
    }

    /**
     * Méthode qui désabonnent de cette interface tous les observateurs abonnés
     */
    @Override
    public void unsubscribeAll() {
        observers.clear();
    }

    /**
     * Méthode qui abonnent tous les observateurs de l'interface à une autre interface
     * @param target L'interface à laquelle les observateurs de l'interface courante seront abonnés
     */
    @Override
    public void transferSubscriptions(IObservable<T> target) {
        observers.forEach(target::subscribe);
    }
}
