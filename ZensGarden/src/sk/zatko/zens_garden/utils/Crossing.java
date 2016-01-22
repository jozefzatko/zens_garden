package sk.zatko.zens_garden.utils;

import java.util.Random;

import sk.zatko.zens_garden.models.Genome;

/*
 * Executes Genome crossing 
 *
 * @pattern Singleton
 *
 * @author Jozef Zatko
 */
public class Crossing {

	private static Crossing instance = null;
	private static Random random;
	
	private Crossing() {}
	
	public static Crossing getInstance() {
		
		if(instance == null) {
			
			instance = new Crossing();
			random = new Random();
		}
		
		return instance;
	}
	
	/*
	 * Cross father and mother genome into child genome
	 */
	public Genome cross(Genome father, Genome mother) {

		int fatherFitness = (int)(father.getFitness() * 100);
		int motherFitness = (int)(mother.getFitness() * 100);
		
		int randomRange = motherFitness + fatherFitness;
		
		// both fitness are 0
		if(randomRange == 0) {
			
			return father;
		}
		
		int value = random.nextInt(randomRange);
		
		Genome child = new Genome(father.getData().length);
			
		// first 75% of genome length if base of genome
		int childGenomeBase;
		childGenomeBase = (int) (father.getData().length * 0.75);
		
		// first 75% of genome is from father or mother
		for(int i=0; i<childGenomeBase; i++) {

			if(value < fatherFitness) {
				
				child.getData()[i] = father.getData()[i];
			}
			else {
				
				child.getData()[i] = mother.getData()[i];
			}
		}
		
		// last 25% of genome is variable
		for(int i=childGenomeBase; i<father.getData().length; i++) {
			
			if(random.nextInt(2) == 0) {
				
				child.getData()[i] = father.getData()[i];
			}
			else {
				
				child.getData()[i] = mother.getData()[i];
			}
		}
		
		// set direction into genome based on father or mother
		for(int i=0; i<father.getData().length; i++) {
			
			if(random.nextInt(2) == 0) {
				
				if(father.getData()[i] < 0 && child.getData()[i] > 0) {
					
					child.getData()[i] *= -1;
				}
				
				if(father.getData()[i] > 0 && child.getData()[i] < 0) {
					
					child.getData()[i] *= -1;
				}
			}
			else {
				
				if(mother.getData()[i] < 0 && child.getData()[i] > 0) {
					
					child.getData()[i] *= -1;
				}
				
				if(mother.getData()[i] > 0 && child.getData()[i] < 0) {
					
					child.getData()[i] *= -1;
				}
			}
		}
		
		return child;
	}
}
