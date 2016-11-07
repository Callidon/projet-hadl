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
     * La liste des interfaces que possède cet élément
     */
    protected Map<String, Interface> interfaces = new HashMap<>();

    /**
     * Constructeur
     * @param interfaces La liste des interfaces que possède cet élément
     */
    public Element(List<Interface> interfaces) {
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
}
