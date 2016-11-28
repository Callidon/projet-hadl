package com.alma.hadl.model.system.server.clearence;

import com.alma.hadl.metamodel.connector.Glue;

import java.util.logging.Logger;

/**
 * Glue qui gère les requêtes d'authentification faites à un gestionnaire de sécurité.
 * Elle transforme les données de String vers un tableau de bytes, car c'est le type attendue par le gestionnaire
 * pou appliquer ses algorithmes de cryptographie.
 * @author Théo Couraud
 * @author Thomas Minier
 */
public class GlueAuthRequest implements Glue<String, byte[]> {
    private static final Logger logger = Logger.getLogger(GlueAuthRequest.class.getName());

    @Override
    public byte[] map(String input) {
        logger.info("[Glue Auth Request] map : " + input + " => " + input.getBytes());
        return input.getBytes();
    }
}
