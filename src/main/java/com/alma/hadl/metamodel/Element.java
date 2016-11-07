package com.alma.hadl.metamodel;

import com.alma.hadl.metamodel.interfaces.Interface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Element représente l'objet de base du métamodèle HADL, dont héritent Composant et Configuration.
 * @author Théo Couraud
 * @author Thomas Minier
 */
public abstract class Element {

    /**
     * Le nom de l'élément
     */
    protected String name;

    /**
     * La liste des interfaces que possède cet élément
     */
    protected Map<String, Interface> interfaces = new HashMap<>();

    /**
     * Constructeur
     * @param interfaces La liste des interfaces que possède cet élément
     */
    public Element(String name, List<Interface> interfaces) {
        this.name = name;
        for(Interface interf : interfaces) {
            this.interfaces.put(interf.getName(), interf);
        }
    }

    /**
     * getInterface permet de récupérer une interface de l'élément par son nom
     * @param name Le nom de l'interface que l'on veut récupérer
     * @return L'interface dont le nom correspond à celle passée en paramètre
     */
    public Interface getInterface(String name) {
        return interfaces.get(name);
    }

    /**
     * Accesseur protégé sur les interfaces de l'élément
     * @return Les interfaces de l'élément
     */
    public Map<String, Interface> getInterfaces() {
        return interfaces;
    }

    /**
     * Accesseur sur le nom de l'élément
     * @return Le nom de l'élément
     */
    public String getName() {
        return name;
    }
}
