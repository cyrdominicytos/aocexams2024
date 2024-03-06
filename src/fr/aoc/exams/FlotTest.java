package fr.aoc.exams;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * Class to Test Flot and FlotPara
 * @author Cyriaque TOSSOU
 * Date : 2024-03-05
 */
public class FlotTest {
	
	private Flot<String> flot;
	private ExecutorService executor = Executors.newSingleThreadExecutor();
	@DisplayName ("Test en parallèle")
	@Test
	void testPara () {
		flot = new FlotPara<>();
		//runBasicTest();
		runAdvancedTest();
	}
	

	/**
	 * Problems  :
	 * 1 : La taille du flot d'entrée doit être un multiple de NB_OF_THREADS=4 
	 *     (sinon certaines valeurs du flot ne seront pas traitées
	 *     
	 * 2 : Etant donné l'utilisation s.length comme Fonction de test, il faudrait
	 *     que la taille des chaines soit tjrs comprise entre 0 et MAX_VALUE = 10
	 *     
	 * 3 : Il faut attendre la fin de l'execution de flot.filter avant de vérifier 
	 *     l'oracle (sinon des traitement seront en cours et donc tous les résultats 
	 *     ne seront pas encore dans le flow de sortie)
	 */
	private void runBasicTest() {
		Collection<String> input = List.of("a", "bc", "cde", "a", "ed", "cde");
		flot.setInput(input);
		// Test de l'opération Flot:: filter 
		flot.filter ((s) -> s. length());
		
		// Oracle
		Collection<String> attendu = List.of("bc", "ed");
		//assertEquals(attendu, flot.getOutput(2));
		((FlotPara<String>)flot).getResultQueue().forEach((s)->{ System.out.println(s+" - ");});
		
	}
	
	/**
	 * Test amélioré
	 */
	private void runAdvancedTest() {
		
		Collection<String> input = List.of("a", "bc", "cde", "a", "ed", "cde","ta", "tdqu");
		flot.setInput(input);
		// Test de l'opération Flot:: filter 
		Future<?> f = executor.submit(()->{
			flot.filter ((s) -> s. length());
		});
		
		// Attente de la fin de l'exécution de tous les threads
		while(!f.isDone()) {}
		
		// Oracle
		Collection<String> attendu = List.of("bc", "ed","ta");
		assertEquals(attendu, flot.getOutput(2).stream().toList());
		((FlotPara<String>)flot).getResultQueue().forEach((s)->{ System.out.println(s+" - ");});
		
	}
	
}
