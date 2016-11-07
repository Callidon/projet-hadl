package com.alma.hadl.metamodel.configuration;

import com.alma.hadl.metamodel.Element;
import com.alma.hadl.metamodel.IObserver;
import com.alma.hadl.metamodel.interfaces.Interface;
import com.alma.hadl.metamodel.interfaces.provided.Provided;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedPort;
import com.alma.hadl.metamodel.interfaces.required.Required;
import com.alma.hadl.metamodel.interfaces.required.RequiredPort;

import java.util.ArrayList;
import java.util.List;

/**
 * Configuration représente une configuration du modèle d'Architecture orientée composant.
 * C'est elle qui coordonne les composants et les connecteurs entre eux, et elle créée les liens
 * d'attchment et de bindings entre les interfaces.
 * @author Théo Couraud
 * @author Thomas Minier
 */
public abstract class Configuration extends Element {
    /**
     * Les éléments (Composants ou Configurations) que la configuration encapsule
     */
    private List<Element> elements;

    /**
     * Constructeur
     * @param interfaces Les interfaces que la configueation possède
     */
    public Configuration(List<Interface> interfaces) {
        super(interfaces);
        elements = new ArrayList<>();
    }

    /**
     * Méthode qui ajoute un élément à la configuration
     * @param element L'élément à ajouter à la configuration
     */
    protected void addElement(Element element) {
        elements.add(element);
    }

    /**
     * Méthode qui créée un lien d'attachment d'un port fourni vers un port requis.
     * Toute donnée reçue par le port fournie sera transférée au port requis grâce à un observateur.
     * @param input Le port fourni dont les données seont issues.
     * @param output Le port requis vers lequel les données seront transmises.
     * @param <T> Le type de données qui transitent via ce lien d'attachment
     */
    protected <T> void attach(Provided<T> input, final Required<T> output) {
        input.subscribe(new IObserver<T>() {
            @Override
            public void update(T data) {
                output.receive(data);
            }
        });
    }

    /**
     * Méthode qui créée un lien de binding entre deux ports fournis.
     * Toute donnée reçue par le 1er port sera transférée au second port grâce à un observateur.
     * @param left Le port dont les données sont issues.
     * @param right Le port vers lequel les données seront transmises.
     * @param <T> Le type de données qui transitent via ce lien de binding
     */
    protected <T> void bind(ProvidedPort<T> left, final ProvidedPort<T> right) {
        left.subscribe(new IObserver<T>() {
            @Override
            public void update(T data) {
                right.notifyObservers(data);
            }
        });
    }

    /**
     * Méthode qui créée un lien de binding entre deux ports requis.
     * Toute donnée reçue par le 1er port sera transférée au second port grâce à un observateur.
     * @param left Le port dont les données sont issues.
     * @param right Le port vers lequel les données seront transmises.
     * @param <T> Le type de données qui transitent via ce lien de binding
     */
    protected <T> void bind(RequiredPort<T> left, final RequiredPort<T> right) {
        left.subscribe(new IObserver<T>() {
            @Override
            public void update(T data) {
                right.receive(data);
            }
        });
    }
}
