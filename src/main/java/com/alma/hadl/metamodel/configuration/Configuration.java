package com.alma.hadl.metamodel.configuration;

import com.alma.hadl.metamodel.Element;
import com.alma.hadl.metamodel.interfaces.Interface;
import com.alma.hadl.metamodel.interfaces.provided.Provided;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedPortComponent;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedPortConfiguration;
import com.alma.hadl.metamodel.interfaces.required.Required;
import com.alma.hadl.metamodel.interfaces.required.RequiredPortComponent;
import com.alma.hadl.metamodel.interfaces.required.RequiredPortConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Configuration représente une configuration du modèle d'Architecture orientée composant.
 * C'est elle qui coordonne les composants et les connecteurs entre eux, et elle créée les liens
 * d'attachments et de bindings entre les interfaces.
 * @author Théo Couraud
 * @author Thomas Minier
 */
public abstract class Configuration extends Element {
    /**
     * Les éléments (Composants ou Configurations) que la configuration encapsule
     */
    private Map<String, Element> elements;

    /**
     * Constructeur
     * @param interfaces Les interfaces que la configuration possède
     */
    public Configuration(String name, List<Interface> interfaces) {
        super(name, interfaces);
        elements = new HashMap<>();
    }

    /**
     * Méthode qui ajoute un élément à la configuration
     * @param element L'élément à ajouter à la configuration
     */
    protected void addElement(Element element) {
        elements.put(element.getName(), element);
    }

    /**
     * Méthode qui créée un lien d'attachment d'un port fourni vers un port requis.
     * Toute donnée reçue par le port fourni sera transférée au port requis grâce à un observateur.
     * @param input Le port fourni dont les données seront issues.
     * @param output Le port requis vers lequel les données seront transmises.
     * @param <T> Le type de donnée qui transite via ce lien d'attachment
     */
    protected <T> void attach(Provided<T> input, final Required<T> output) {
        input.subscribe(data -> output.receive(data));
    }

    /**
     * Méthode qui créée un lien de binding entre deux ports fournis de configuration vers composant.
     * Toute donnée reçue par le 1er port sera transférée au second port grâce à un observateur.
     * @param left Le port dont les données sont issues.
     * @param right Le port vers lequel les données seront transmises.
     * @param <T> Le type de donnée qui transite via ce lien de binding
     */
    protected <T> void bind(ProvidedPortConfiguration<T> left, final ProvidedPortComponent<T> right) {
        left.subscribe(data -> right.notifyObservers(data));
    }

    /**
     * Méthode qui créée un lien de binding entre deux ports fournis de composant vers configuration
     * Toute donnée reçue par le 1er port sera transférée au second port grâce à un observateur.
     * @param left Le port dont les données sont issues.
     * @param right Le port vers lequel les données seront transmises.
     * @param <T> Le type de donnée qui transite via ce lien de binding
     */
    protected <T> void bind(ProvidedPortComponent<T> left, final ProvidedPortConfiguration<T> right) {
        left.subscribe(data -> right.notifyObservers(data));
    }

    /**
     * Méthode qui créée un lien de binding entre deux ports fournis de configuration
     * Toute donnée reçue par le 1er port sera transférée au second port grâce à un observateur.
     * @param left Le port dont les données sont issues.
     * @param right Le port vers lequel les données seront transmises.
     * @param <T> Le type de donnée qui transite via ce lien de binding
     */
    protected <T> void bind(ProvidedPortConfiguration<T> left, final ProvidedPortConfiguration<T> right) {
        left.subscribe(data -> right.notifyObservers(data));
    }

    /**
     * Méthode qui créée un lien de binding entre deux ports requis de configuration vers composant
     * Toute donnée reçue par le 1er port sera transférée au second port grâce à un observateur.
     * @param left Le port dont les données sont issues.
     * @param right Le port vers lequel les données seront transmises.
     * @param <T> Le type de donnée qui transite via ce lien de binding
     */
    protected <T> void bind(RequiredPortConfiguration<T> left, final RequiredPortComponent<T> right) {
        left.subscribe(data -> right.receive(data));
    }

    /**
     * Méthode qui créée un lien de binding entre deux ports requis de composant vers configuration
     * Toute donnée reçue par le 1er port sera transférée au second port grâce à un observateur.
     * @param left Le port dont les données sont issues.
     * @param right Le port vers lequel les données seront transmises.
     * @param <T> Le type de donnée qui transite via ce lien de binding
     */
    protected <T> void bind(RequiredPortComponent<T> left, final RequiredPortConfiguration<T> right) {
        left.subscribe(data -> right.receive(data));
    }

    /**
     * Méthode qui créée un lien de binding entre deux ports requis de configuration
     * Toute donnée reçue par le 1er port sera transférée au second port grâce à un observateur.
     * @param left Le port dont les données sont issues.
     * @param right Le port vers lequel les données seront transmises.
     * @param <T> Le type de donnée qui transite via ce lien de binding
     */
    protected <T> void bind(RequiredPortConfiguration<T> left, final RequiredPortConfiguration<T> right) {
        left.subscribe(data -> right.receive(data));
    }
}
