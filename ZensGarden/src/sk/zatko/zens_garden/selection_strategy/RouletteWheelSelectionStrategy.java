package sk.zatko.zens_garden.selection_strategy;

import java.util.Random;

import sk.zatko.zens_garden.models.Garden;
import sk.zatko.zens_garden.models.Genome;
import sk.zatko.zens_garden.models.Population;
import sk.zatko.zens_garden.utils.Crossing;
import sk.zatko.zens_garden.utils.Mutator;

/*
 * Implementation of Roultee Wheel Selection method to create new population
 * 
 * @pattern Strategy
 * 
 * @author Jozef Zatko
 */
public class RouletteWheelSelectionStrategy implements SelectionStrategy {

	private int populationSize;
	private int elitsmCount;
	
	private int startChangeChance;
	private int behaviorChangeChance;
	private int genomeChangeChance;
	
	private Random random = null;
	
	
	public RouletteWheelSelectionStrategy(int populationSize, int elitsmCount, int startChangeChance, int behaviorChangeChance, int genomeChangeChance) {
		
		this.populationSize = populationSize;
		this.elitsmCount = elitsmCount;
		
		this.startChangeChance = startChangeChance;
		this.behaviorChangeChance = behaviorChangeChance;
		this.genomeChangeChance = genomeChangeChance;
		
		this.random = new Random();
	}

	
	public Population createNewPopulation(Population population, Garden garden) {

		Population newPopulation = new Population(this.populationSize, garden);
		
		int maximum = 0;
		
		int[] values = new int[this.populationSize]; 
		
		for(int i=0; i<this.populationSize; i++) {
			
			maximum += (int)(population.getGenomes()[i].getFitness() * 100); 
			values[i] = maximum;
		}
		
		for(int i=0; i<elitsmCount; i++) {
			
			newPopulation.getGenomes()[i] = population.getGenomes()[i];
		}
		
		for(int i=elitsmCount; i<this.populationSize; i++) {
			
			Genome father = getParent(maximum, values, population);
			Genome mother = getParent(maximum, values, population);
			
			Genome child = Crossing.getInstance().cross(father, mother);
			
			child = Mutator.getInstance().makeStartPositionsMutation(child, this.startChangeChance, garden);
			child = Mutator.getInstance().makeBehaviorMutatation(child, this.behaviorChangeChance);
			child = Mutator.getInstance().makeGenChangeMutation(child, this.genomeChangeChance);

			newPopulation.getGenomes()[i] = child;
		}

		return newPopulation;
	}
	
	
	public String toString() {
	
		return "Roulette wheel selection";
	}
	
	
	private Genome getParent(int maximum, int[] values, Population population) {
		
		
		int rndValue = this.random.nextInt(maximum)+1;
		
		for(int i=0; i<this.populationSize; i++) {
			
			if(rndValue <= values[i]) {
				
				return population.getGenomes()[i];
			}
		}
		return population.getGenomes()[0];
	}
}
