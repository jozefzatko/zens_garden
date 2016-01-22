package sk.zatko.zens_garden.utils;

import java.util.Random;

import sk.zatko.zens_garden.models.Garden;
import sk.zatko.zens_garden.models.Genome;

/*
 * Provide mutation of genome
 * 
 * @author Jozef Zatko
 */
public class Mutator {
	
	private static Mutator instance = null;
	private static Random random;
	
	private Mutator() {}

	public static Mutator getInstance() {
		
		if(instance == null) {
			
			instance = new Mutator();
			random = new Random();
		}
		return instance;
	}
	
	
	/*
	 * Change first gene with random gene in genome - start position of move through garden
	 */
	public Genome makeStartPositionsMutation(Genome genome, int mutationProbability, Garden garden) {
		
		if(mutationProbability == 0) {
			
			return genome;
		}
		
		if(random.nextInt(100) < mutationProbability) {
				
			int rndValue = random.nextInt(genome.getData().length - 1) + 1;
			
			int temp = genome.getData()[0];
			genome.getData()[0] = genome.getData()[rndValue];
			genome.getData()[rndValue] = temp;	
		}
	
		return genome;
	}
	
	
	/*
	 * Change sign of gene in genome - direction of move through garden
	 */
	public Genome makeBehaviorMutatation(Genome genome, int mutationProbability) {
		
		if(mutationProbability == 0) {
			
			return genome;
		}
		
		int length = genome.getData().length;
		
		for(int i=0; i<length; i++) {
			
			if(random.nextInt(100) < mutationProbability) {
				
				genome.getData()[i] *= -1;
			}
		}
		
		return genome;
	}

	
	/*
	 * Change random gene with other gene in genome
	 */
	public Genome makeGenChangeMutation(Genome genome, int mutationProbability) {
		
		if(mutationProbability == 0) {
			
			return genome;
		}
		
		int length = genome.getData().length;
		
		for(int i=1; i<length; i++) {
			
			if(random.nextInt(100) < mutationProbability) {
				
				int secondGenPosition = random.nextInt(length);
				int tempGen = genome.getData()[secondGenPosition];
				
				genome.getData()[secondGenPosition] = genome.getData()[i];
				genome.getData()[i] = tempGen;
			}
		}
		
		return genome;
	}
}
