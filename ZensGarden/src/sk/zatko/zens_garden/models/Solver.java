package sk.zatko.zens_garden.models;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import sk.zatko.zens_garden.selection_strategy.RankSelectionStrategy;
import sk.zatko.zens_garden.selection_strategy.RouletteWheelSelectionStrategy;
import sk.zatko.zens_garden.selection_strategy.SelectionStrategy;
import sk.zatko.zens_garden.selection_strategy.TournamentSelectionStrategy;
import sk.zatko.zens_garden.ui.ResultsGUI;
import sk.zatko.zens_garden.ui.UserInterface;

/*
 * Model of Solver of Zen's Garden 
 * 
 * @author Jozef Zatko
 */
public class Solver implements Runnable {

	private UserInterface mainGui;
	private ResultsGUI resultsGui;
	
	private Garden garden;	
	private SelectionStrategy selectionStrategy;
	
	private int populationSize;
	
	private String gardenFile;
	private int numberOfIterations;
	
	
	public Solver(UserInterface mainGui, ResultsGUI resultsGui) {
		
		this.mainGui = mainGui;
		this.resultsGui = resultsGui;
		
		Properties properties = new Properties();
		InputStream in = null;
		try {
			in = new FileInputStream("settings.properties");
		} catch (FileNotFoundException e1) {

			this.gardenFile = "input_txt";
			this.numberOfIterations = 1;
			
			e1.printStackTrace();
		}
		
		try {
			properties.load(in);
			
		} catch (IOException e) {
			
			this.gardenFile = "input_txt";
			this.numberOfIterations = 1;
			
			e.printStackTrace();
		}
		
		this.gardenFile = properties.getProperty("garden_file");
		this.numberOfIterations = Integer.parseInt(properties.getProperty("number_of_iterations"));
		
		
		try {
			if(in != null) {
				in.close();
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	
	public void run() {

		for(int i=0; i<numberOfIterations; i++) {
			
			resultsGui.post("---------------------------------------------------\n");
			resultsGui.post(i+1 + ". iteration\n\n");
			
			garden = new Garden(gardenFile);
			
			this.populationSize = (int)mainGui.getPopulationSizeSpinner().getValue();
			
			Population population = new Population(populationSize,garden);
			
			population.add();
			population.evaluate();
			population.sort();
			
			this.resultsGui.post("0. generation:\n");
			
			double geneFitness;
			double totalFitness = 0;
			
			for(int j=0; j<populationSize; j++) {
				
				geneFitness = population.getFitness()[j];
				
				totalFitness = totalFitness + geneFitness;
				resultsGui.post((double)(Math.round(geneFitness*1000)/10.0) + "%\t");
			}
			resultsGui.post("\nAverege fitness: " + (Math.round(totalFitness/population.getSize()*10000))/100.0 + "%\n\n");
			
			
			int maxGeneration = (int) mainGui.getMaxGenerationSpinner().getValue();
			
	
			if(mainGui.getSelectionStrategyComboBox().getSelectedIndex() == 0) {
				
				selectionStrategy = new RouletteWheelSelectionStrategy((int) mainGui.getPopulationSizeSpinner().getValue(), 
						(int) mainGui.getEliteCountSpinner().getValue(),
						(int) mainGui.getStartPositionChangeSpinner().getValue(),
						(int) mainGui.getBehaviorChangeSpinner().getValue(),
						(int) mainGui.getGenomeChangeSpinner().getValue());
			}

			if(mainGui.getSelectionStrategyComboBox().getSelectedIndex() == 1) {
				
				selectionStrategy = new RankSelectionStrategy((int) mainGui.getPopulationSizeSpinner().getValue(), 
						(int) mainGui.getEliteCountSpinner().getValue(),
						(int) mainGui.getStartPositionChangeSpinner().getValue(),
						(int) mainGui.getBehaviorChangeSpinner().getValue(),
						(int) mainGui.getGenomeChangeSpinner().getValue());
			}
	
			if(mainGui.getSelectionStrategyComboBox().getSelectedIndex() == 2) {
		
				selectionStrategy = new TournamentSelectionStrategy((int) mainGui.getPopulationSizeSpinner().getValue(), 
						(int) mainGui.getEliteCountSpinner().getValue(),
						(int) mainGui.getStartPositionChangeSpinner().getValue(),
						(int) mainGui.getBehaviorChangeSpinner().getValue(),
						(int) mainGui.getGenomeChangeSpinner().getValue(),
						(int) mainGui.getTournamentSizeSpinner().getValue());
			}
			
			for(int j=0; j<maxGeneration; j++) {
				
				mainGui.getProgressBar().setValue(1 + (j * 100) / maxGeneration);
				
				population = selectionStrategy.createNewPopulation(population, garden);
				population.evaluate();
				population.sort();
				
				this.resultsGui.post("\n\n" + (j+1) +". generation:\n");
				
				totalFitness = 0;
				for(int k=0; k<populationSize; k++) {
					
					geneFitness = population.getFitness()[k];
					
					totalFitness = totalFitness + geneFitness;
					resultsGui.post((double)(Math.round(geneFitness*1000)/10.0) + "%\t");
				}
				
				resultsGui.post("\nAverage fitness: " + (Math.round(totalFitness/population.getSize()*10000))/100.0 + "%\n");
			}
			
			resultsGui.post("\n\n");
		}	
		
		mainGui.getBtnStart().setText("Start");
		resultsGui.setVisible(true);
	}
}
