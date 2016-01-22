package sk.zatko.zens_garden.selection_strategy;

import java.util.Random;

import sk.zatko.zens_garden.models.Garden;
import sk.zatko.zens_garden.models.Genome;
import sk.zatko.zens_garden.models.Population;
import sk.zatko.zens_garden.utils.Crossing;
import sk.zatko.zens_garden.utils.Mutator;

/*
 * Implementation of Rank Selection method to create new population
 * 
 * @pattern Strategy
 * 
 * @author Jozef Zatko
 */
public class RankSelectionStrategy implements SelectionStrategy{

	private int populationSize;
	private int elitsmCount;
	
	private int startChangeChance;
	private int behaviorChangeChance;
	private int genomeChangeChance;
	
	private Random random = null;
	
	
	public RankSelectionStrategy(int populationSize, int elitsmCount, int startChangeChance, int behaviorChangeChance, int genomeChangeChance) {
		
		this.populationSize = populationSize;
		this.elitsmCount = elitsmCount;
		
		this.startChangeChance = startChangeChance;
		this.behaviorChangeChance = behaviorChangeChance;
		this.genomeChangeChance = genomeChangeChance;
		
		this.random = new Random();
	}
	
	
	public Population createNewPopulation(Population population, Garden garden) {
		
		Population newPopulation = new Population(populationSize, garden);
		
		for(int i=0; i<elitsmCount; i++) {
			
			newPopulation.getGenomes()[i] = population.getGenomes()[i];
		}
		
		int maximum = (populationSize + populationSize*populationSize) / 2;
		
		for(int i=elitsmCount; i<this.populationSize; i++) {
			
			Genome father = getParent(maximum, population);
			Genome mother = getParent(maximum, population);
			
			Genome child = Crossing.getInstance().cross(father, mother);
			
			child = Mutator.getInstance().makeStartPositionsMutation(child, this.startChangeChance, garden);
			child = Mutator.getInstance().makeBehaviorMutatation(child, this.behaviorChangeChance);
			child = Mutator.getInstance().makeGenChangeMutation(child, this.genomeChangeChance);
			
			newPopulation.getGenomes()[i] = child;
		}

		return newPopulation;
	}
	
	
	public String toString() {
		
		return "Rank selection";
	}
	
	
	private Genome getParent(int maximum, Population population) {
	
		int value = random.nextInt(maximum) + 1;
		
		double dIndex = ((Math.sqrt(value*8 + 1)-1)/2);		
		int index = (int) Math.ceil(dIndex);
		
		return population.getGenomes()[index-1];
	}
}

