package sk.zatko.zens_garden.utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Loader of Garden model from text file
 */
public class FromFileToGarden {

	private int xSize;
	private int ySize;
	
	private int gardenMatrix[][];
	private int countOfRocks;
	
	
	public FromFileToGarden(String fileName) {
		
		loadGardenFromFile(fileName);
	}
	
	
	/*
	 * Loads garden from file
	 */
	private boolean loadGardenFromFile(String fileName) {
		
		this.countOfRocks = 0;
		
		FileInputStream fstream;
		
		try {
			fstream = new FileInputStream(fileName);
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
			return false;
		}
		
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
		
		String firstLine = "";
		
		try {
			firstLine = bufferedReader.readLine();
		} catch (IOException e) {
			
			e.printStackTrace();
			
			try {
				bufferedReader.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			return false;
		}

		this.xSize = Integer.parseInt(firstLine.substring(0, firstLine.indexOf(" ")));
		this.ySize = Integer.parseInt(firstLine.substring(firstLine.indexOf(" ") + 1, firstLine.length()));
		
		this.gardenMatrix = new int[this.ySize][this.xSize];

		String fileLine = "";

		
		for(int i=0; i<ySize; i++) {
			
			try {
				fileLine = bufferedReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			for(int j=0; j<xSize; j++) {
				
				gardenMatrix[i][j] = 0;
				
				if(fileLine.substring(2*j, 2*j+1).equals("K")) {
					
					gardenMatrix[i][j] = -1;
					this.countOfRocks += 1;
				}
			}
		}
		
		try {
			bufferedReader.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return true;
	}

	public int getxSize() {
		return xSize;
	}

	public int getySize() {
		return ySize;
	}

	public int[][] getGardenMatrix() {
		return gardenMatrix;
	}

	public int getCountOfRocks() {
		return countOfRocks;
	}
}
