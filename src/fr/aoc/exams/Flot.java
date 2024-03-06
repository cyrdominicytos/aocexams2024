package fr.aoc.exams;

import java.util.Collection;

/**
 * Define an interface of Flot
 * @author Cyriaque TOSSOU
 * Date : 2024-03-05
 * @param <T>, the generic type of Flot
 */
public interface Flot<T> {
	static final int MAX_VALUE = 10;
	
	/**
	 * Détermine la source de donnée pour le flot
	 * @param input
	 */
	void setInput (Collection<T> input);
	
	/**
	 * ﻿﻿Evalue pour chaque élément e du flot une fonction passée en paramètre
	 * ﻿﻿et range l'éléemnt dans la collection de sortie numéro n,ou n est la valeur retournee par la fonction pour l'e1ement e
	 * 
	 * @param function une fonction de valeur comprise entre 0 et MAX_VALUE
	 */
	void filter (Function<T, Integer> function);

	/**
	 * Permet de récupérer le contenu de la collection
	 * de sortie numéro i, qui contient les éléments filtrés
	 * pour lesquels la fonction a donné la valeur i﻿﻿
	 * @param i
	 * @return la ième collection de sortie
	 */
	Collection<T> getOutput(int i);
	
	
}
	