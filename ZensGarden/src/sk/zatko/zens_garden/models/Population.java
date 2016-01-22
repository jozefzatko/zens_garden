package sk.zatko.zens_garden.models;

/*
 * Model of Population
 * 
 * @author Jozef Zatko
 */
public class Population {

	private int level;
	private int size;
	private Garden garden;
	
	private Genome[] genomes;
	private double[] fitness;
	
	
	public Population(int size, Garden garden) {
		
		this.garden = garden;
		
		this.size = size;
		
		this.genomes = new Genome[size];
		this.fitness = new double[size];
	}
	
	
	/*
	 * Add random genomes into first generation
	 */
	public void add() {
		
		Genome genome = null;
		
		for(int i=0; i<size; i++) {
			
			genome = new Genome(garden.getGardenCircumference()/2 + garden.getCountOfRocks());
			genome.randomGenGenerator(garden.getGardenCircumference());
			genomes[i] = genome;
		}
	}
	
	
	/*
	 * Count fitness function for all genomes in generation
	 */
	public void evaluate() {
				
		for(int i=0; i<size; i++) {
			
			this.garden.reset();
			garden.gardenWalk(this.genomes[i]);
			
			genomes[i].setFitness(garden.getFitness());
			fitness[i] = garden.getFitness();				
		}	
	}
	
	
	/*
	 * Sort genomes in population according to fitness function
	 */
	public void sort() {
		
		quickSort(genomes,fitness,0,size-1);
	}	
			
	
	/*
	 * QuicSort implementation for genomes in population
	 */
	private void quickSort(Genome[] g, double[] f, int low, int high) {
			    
		int i = low, j = high;
		
		double pivot = f[low + (high-low)/2];

		while (i <= j) {

			while (f[i] > pivot) {
				
				i++;
			}

			while (f[j] < pivot) {
			        
				j--;
			}

			if (i <= j) {
			        
				exchange(i, j);
			    i++;
			    j--;
			}
		}
		
		if (low < j) {
			      
			quickSort(g,f,low,j);
		}
		
			    
		if (i < high) {
			
			quickSort(g,f,i,high);
		}
	}
	
	
	/*
	 * Change 2 genomes in population, used in quickSort
	 */
	private void exchange(int i, int j) {
		
		Genome g = this.genomes[i];
		double f = this.fitness[i];
		
		this.genomes[i] = this.genomes[j];
		this.fitness[i] = this.fitness[j];
		
		this.genomes[j] = g;
		this.fitness[j] = f;		
	}
	

	public int getLevel() {
		return level;
	}

	public int getSize() {
		return size;
	}

	public Garden getGarden() {
		return garden;
	}

	public Genome[] getGenomes() {
		return genomes;
	}

	public double[] getFitness() {
		return fitness;
	}
}
