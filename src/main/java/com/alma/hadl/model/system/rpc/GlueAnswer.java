package com.alma.hadl.model.system.rpc;

import com.alma.hadl.metamodel.connector.Glue;

import java.util.Properties;

/**
 * Glue qui représente la glue du modèle d'Architecture orientée composant.
 * Cette glue est la glue qui envoie les requêtes retour et les retournes après une possible modification.
 * @author Théo Couraud
 * @author Thomas Minier
 */
public class GlueAnswer implements Glue<Properties, Properties> {
    @Override
    public Properties map(Properties input) {
        return input;
    }
}
