package sk.zatko.zens_garden.selection_strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sk.zatko.zens_garden.models.Garden;
import sk.zatko.zens_garden.models.Genome;
import sk.zatko.zens_garden.models.Population;
import sk.zatko.zens_garden.utils.Crossing;
import sk.zatko.zens_garden.utils.Mutator;

/*
 * Implementation of Tournament Selection method to create new population
 * 
 * @pattern Strategy
 * 
 * @author Jozef Zatko
 */
public class TournamentSelectionStrategy implements SelectionStrategy {

	private int populationSize;
	private int elitsmCount;
	
	private int startChangeChance;
	private int behaviorChangeChance;
	private int genomeChangeChance;
	
	private int tournamentSize;
	
	public TournamentSelectionStrategy(int populationSize, int elitsmCount, int startChangeChance, int behaviorChangeChance, int genomeChangeChance, int tournamentSize) {
		
		this.populationSize = populationSize;
		this.elitsmCount = elitsmCount;
		
		this.startChangeChance = startChangeChance;
		this.behaviorChangeChance = behaviorChangeChance;
		this.genomeChangeChance = genomeChangeChance;
		
		this.tournamentSize = tournamentSize;
	}

	
	public Population createNewPopulation(Population population, Garden garden) {
		
		Population newPopulation = new Population(populationSize, garden);
		
		List<Integer> integers = new ArrayList<Integer>();
		
		for(int i=0; i<this.populationSize; i++) {
			
			integers.add(i);
		}
		
		for(int i=0; i<this.elitsmCount; i++) {
			
			newPopulation.getGenomes()[i] = population.getGenomes()[i];
		}
		
		for(int i=this.elitsmCount; i<this.populationSize; i++) {
					
			Genome tempGenomes[] = new Genome[this.tournamentSize];
			
			Collections.shuffle(integers);
			for(int j=0; j<this.tournamentSize; j++) {
			
				tempGenomes[j] = population.getGenomes()[integers.get(j)];
			}
			Genome father = getParent(tempGenomes, this.tournamentSize);
			
			
			Collections.shuffle(integers);
			for(int j=0; j<this.tournamentSize; j++) {
			
				tempGenomes[j] = population.getGenomes()[integers.get(j)];
			}
			Genome mother = getParent(tempGenomes, this.tournamentSize);
			
			
			Genome child = Crossing.getInstance().cross(father, mother);
		
			child = Mutator.getInstance().makeStartPositionsMutation(child, this.startChangeChance, garden);
			child = Mutator.getInstance().makeBehaviorMutatation(child, this.behaviorChangeChance);
			child = Mutator.getInstance().makeGenChangeMutation(child, this.genomeChangeChance);
				
			newPopulation.getGenomes()[i] = child;
		}

		return newPopulation;
	}
	
	
	public String toString() {
		
		return "Tournament selection";
	}
	
	
	private Genome getParent(Genome[] genomes, int tournamentSize) {
		
		if(tournamentSize == 1) {
			
			return genomes[0];
		}
		
		int best = 0;
		
		for(int i=1; i<tournamentSize; i++) {
			
			if(genomes[i].getFitness() > genomes[best].getFitness()) {
				
				best = i;
			}
			
		}
		
		return genomes[best];
	}
}
