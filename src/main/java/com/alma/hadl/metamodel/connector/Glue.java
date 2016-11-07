package com.alma.hadl.metamodel.connector;

/**
 * Glue représente une glue qui transforme des données d'un type vers un autre.
 * Elle est utlisée par un connecteur pour faire correspondre les données d'un rôle requis à un port fourni.
 * @author Théo Couraud
 * @author Thomas Minier
 */
@FunctionalInterface
public interface Glue<I,O> {
    /**
     * Méthode qui effectue la correspodance des données d'un type vers un autre
     * @param input Les données en entrée
     * @return Les données transformées par la glue
     */
    O map(I input);
}
