package fr.aoc.exams;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Define an implemention of Flot
 * @author Cyriaque TOSSOU
 * Date : 2024-03-05
 * @param <T>, the generic type of Flot
 */
public class FlotPara<T> implements Flot<T> {
	final static int OUTPUT_SIZE = 1000;
	final static int NB_OF_THREADS = 4;
	private BlockingQueue<T> inputQueue;
	private ExecutorService executor = Executors.newCachedThreadPool();

	//TODO Compléter avec d'autres attributs nécessaires au code plus bas
	//private Collection<Collection<T>> output;
	private List<BlockingQueue<T>> outputQueue;
	public FlotPara () {
		outputQueue = new ArrayList<>(this.MAX_VALUE+1);
		for(int i=0; i<=this.MAX_VALUE; i++)
			outputQueue.add(i,  new ArrayBlockingQueue<T>(OUTPUT_SIZE));
	}
	
	
	@Override
	public void setInput(Collection<T> input) {
		inputQueue = new ArrayBlockingQueue<>(input.size(), false, input);
		//output = new ArrayList<>(this.MAX_VALUE+1);
		
	}
	@Override
	public void filter(Function<T, Integer> function) {
		// TODO
		// Use Active Object to provide NB_THREADS parallel executions of
		// the seqFilter method
		int n = inputQueue.size()/NB_OF_THREADS;
		for(int i=0; i<NB_OF_THREADS; i++) {
			executor.submit(()->{
				this.seqFilter(n, function);
			});
		}
		
	
	}
	
	//Reads data from the input queue and dispatch it in an output Queue
	//depending on the function value
	private void seqFilter(int nboFilter, Function<T, Integer> function) {
		while(nboFilter > 0) {
			try {
				
				T val = inputQueue.take();
				Integer result = function.apply(val);
				outputQueue.get(result).put(val);
			} catch (InterruptedException e) {
				e.printStackTrace();
			
			} 
			
			nboFilter--;
		}
	}

	@Override
	public Collection<T> getOutput(int i) {
		
		if(i>=0 && i<=this.MAX_VALUE)
			return outputQueue.get(i);
		else return null;
	}

	public List<BlockingQueue<T>> getResultQueue() {
		return outputQueue;
	}
}
