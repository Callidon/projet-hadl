package com.alma.hadl.metamodel.connector;

import com.alma.hadl.metamodel.Element;
import com.alma.hadl.metamodel.IObserver;
import com.alma.hadl.metamodel.interfaces.Interface;
import com.alma.hadl.metamodel.interfaces.provided.Provided;
import com.alma.hadl.metamodel.interfaces.required.Required;

import java.util.ArrayList;
import java.util.List;

/**
 * Connector qui représente un Connecteur du modèle d'Architecture orientée composant.
 * Il assure la connexion entre les interfaces de deux composants.
 * @author Théo Couraud
 * @author Thomas Minier
 */
public abstract class Connector extends Element {
    /**
     * Les glues utilisées par le connecteur
     */
    private List<Glue> glues;

    /**
     * Constructeur
     * @param interfaces Les interfaces que le connecteur possède
     */
    public Connector(List<Interface> interfaces) {
        super(interfaces);
        glues = new ArrayList<>();
    }

    /**
     * Méthode qui ajoute une glue au connecteur et l'utilise pour gérer le transfert de données entre deux rôles.
     * @param glue La glue utilisée pour le transfert de données.
     * @param input Le rôle requis dont les données seront issues.
     * @param output Le rôle fourni vers lequel les données seront transmises par la glue.
     * @param <I> Le type des données en entrée de la glue.
     * @param <O> Le type des données en sortie de la glue.
     */
    protected <I,O> void addGlue(final Glue<I, O> glue, Required<I> input, final Provided<O> output) {
        glues.add(glue);
        input.subscribe(new IObserver<I>() {
            @Override
            public void update(I data) {
                output.send(glue.map(data));
            }
        });
    }

}
