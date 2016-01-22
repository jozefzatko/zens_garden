package sk.zatko.zens_garden.models;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/*
 * Model of Genome
 * 
 * @author Jozef Zatko
 */
public class Genome {

	private int data[];
	private double fitness;

	
	public Genome (int size) {
		
		data = new int[size];
	}

	
	/*
	 * Fill genome with random numbers based on garden circumference
	 */
	public void randomGenGenerator(int gardenCircumference) {
		
		List<Integer> integers = new ArrayList<Integer>();
		
		for(int i=0; i<gardenCircumference; i++) {
				
			integers.add(i+1);
		}
		Collections.shuffle(integers);
		
		for(int i=0; i<data.length; i++) {
			
			Random rnd = new Random();
			
			if(rnd.nextInt(2) == 0) {
				
				this.data[i] = integers.get(i);
			}
			else {
				
				this.data[i] = integers.get(i) * -1;
			}
		}
	}
	
	
	public String toString() {
		
		String string = "|";
		
		for(int i=0; i<this.data.length; i++) {
			
			string += this.data[i] + "|";
		}
		
		return string;
	}

	
	public int[] getData() {
		return data;
	}
	
	public double getFitness() {
		return fitness;
	}
	
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
}
