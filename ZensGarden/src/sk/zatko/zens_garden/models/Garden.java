package sk.zatko.zens_garden.models;

import sk.zatko.zens_garden.utils.FromFileToGarden;
import sk.zatko.zens_garden.utils.Mapper;

/*
 * Model of Zen's Garden
 * 
 * @author Jozef Zatko
 */
public class Garden {

	private int xSize;
	private int ySize;
	private int gardenMatrix[][];
	private int countOfRocks;
	private Mapper mapper;
	
	
	public Garden(String fileName) {
				
		FromFileToGarden garden = new FromFileToGarden(fileName);
		
		this.xSize = garden.getxSize();
		this.ySize = garden.getySize();
		
		this.gardenMatrix = garden.getGardenMatrix();
		this.countOfRocks = garden.getCountOfRocks();
		
		this.mapper = new Mapper(this);
	}
	
	
	/*
	 * Implementation of garden walk
	 */
	public void gardenWalk(Genome genome) {
		
		int genomLength = this.getGardenCircumference()/2 + this.getCountOfRocks();
		
		int data;
		char flag;
		
		int xChange = 0;
		int yChange = 0;
		
		for(int i=0; i<genomLength; i++) {
			
			data = genome.getData()[i];
			
			if(data < 0) {
				
				flag = 'L';
				data = data * (-1);
			}
			else {
				flag = 'R';
			}

			int x = this.mapper.getXValue(data);
			int y = this.mapper.getYValue(data);
			
			if(this.mapper.isHorizontal(data)) {
				
				yChange = 0;
				
				if(x == 0) {
					
					xChange = 1;
				}
				else {
					xChange = -1;
				}
			}
			
			if(this.mapper.isVertical(data)) {
				
				xChange = 0;
				
				if(y == 0) {
					
					yChange = 1;
				}
				else {
					yChange = -1;
				}
			}
			
			try {
				makeWalk(x,y,flag,i+1,xChange,yChange);
				
			} catch(ArrayIndexOutOfBoundsException e) {
				return;
			}
		}
	}
	
	
	/*
	 * Recursive implementation of walk move through garden
	 */
	public void makeWalk(int x, int y, char flag, int order, int xChange, int yChange) {
		
		int xPosition = x;
		int yPosition = y;
		
		while(true) {
			
			try {
				if(this.gardenMatrix[yPosition][xPosition] != 0) {
						
					return;
				}
			} catch(ArrayIndexOutOfBoundsException e) {
				
				e.printStackTrace();
				return;
			}
			
			this.gardenMatrix[yPosition][xPosition] = order;
			
			// move up
			if(xChange == 0 && yChange == -1) {
				
				if(yPosition == 0) {
					
					return;
				}
				
				// direction is blocked
				if(this.gardenMatrix[yPosition - 1][xPosition] != 0) {
					
					// turn left
					if(flag == 'L') {
						
						if(xPosition == 0 && this.gardenMatrix[yPosition][xPosition + 1] == 0){
								
							makeWalk(xPosition + 1,yPosition,'L',order,1,0);
							return;
						}
					
						if(this.gardenMatrix[yPosition][xPosition - 1] != 0 && this.gardenMatrix[yPosition][xPosition + 1] == 0) {
								
							makeWalk(xPosition + 1,yPosition,'L',order,1,0);
							return;
						}
						
						if(this.gardenMatrix[yPosition][xPosition - 1] == 0) {
							
							makeWalk(xPosition - 1,yPosition,'L',order,-1,0);
							return;
						}
					}
					
					// turn right
					if(flag == 'R') {
						
						if(xPosition == this.xSize-1 && this.gardenMatrix[yPosition][xPosition - 1] == 0){
							
							makeWalk(xPosition - 1,yPosition,'R',order,-1,0);
							return;
						}
						
						if(this.gardenMatrix[yPosition][xPosition + 1] != 0 && this.gardenMatrix[yPosition][xPosition - 1] == 0) {
							
							makeWalk(xPosition - 1,yPosition,'R',order,-1,0);
							return;
						}
						
						if(this.gardenMatrix[yPosition][xPosition + 1] == 0) {
							
							makeWalk(xPosition + 1,yPosition,'R',order,1,0);
							return;
						}
					}
				}
			}
			
			
			// move down
			if(xChange == 0 && yChange == 1) {
							
				if(yPosition == this.ySize - 1) {
					
					return;
				}
				
				// direction is blocked
				if(this.gardenMatrix[yPosition + 1][xPosition] != 0) {
					
					// turn left
					if(flag == 'L') {
						
						if(xPosition == this.xSize - 1 && this.gardenMatrix[yPosition][xPosition - 1] == 0){
							
							makeWalk(xPosition - 1,yPosition,'L',order,-1,0);
							return;
						}
						
						if(this.gardenMatrix[yPosition][xPosition + 1] != 0 && this.gardenMatrix[yPosition][xPosition - 1] == 0) {
							
							makeWalk(xPosition - 1,yPosition,'L',order,-1,0);
							return;
						}
						
						if(this.gardenMatrix[yPosition][xPosition + 1] == 0) {
							
							makeWalk(xPosition + 1,yPosition,'L',order,1,0);
						}
					}
					
					// turn right
					if(flag == 'R') {
						
						if(xPosition == 0 && this.gardenMatrix[yPosition][xPosition + 1] == 0){
							
							makeWalk(xPosition + 1,yPosition,'R',order,1,0);
							return;
						}
						
						if(this.gardenMatrix[yPosition][xPosition - 1] != 0 && this.gardenMatrix[yPosition][xPosition + 1] == 0) {
							
							makeWalk(xPosition + 1,yPosition,'R',order,1,0);
							return;
						}
						
						if(this.gardenMatrix[yPosition][xPosition - 1] == 0) {
							
							makeWalk(xPosition - 1,yPosition,'R',order,-1,0);
							return;
						}
					}
				}
			}
			
			
			//move left
			if(xChange == -1 && yChange == 0) {
				
				if(xPosition == 0) {
					
					return;
				}
				
				// direction is blocked
				if(this.gardenMatrix[yPosition][xPosition - 1] != 0) {
					
					// turn left
					if(flag == 'L') {
						
						if(yPosition == this.xSize - 1 && this.gardenMatrix[yPosition - 1][xPosition] == 0){
							
							makeWalk(xPosition,yPosition - 1,'L',order,0,-1);
							return;
						}
						
						if(this.gardenMatrix[yPosition + 1][xPosition] != 0 && this.gardenMatrix[yPosition - 1][xPosition] == 0) {
							
							makeWalk(xPosition,yPosition - 1,'L',order,0,-1);
							return;
						}
						
						if(this.gardenMatrix[yPosition + 1][xPosition] == 0) {
							
							makeWalk(xPosition,yPosition + 1,'L',order,0,1);
						}
					}
					
					// turn right
					if(flag == 'R') {
						
						if(yPosition == 0 && this.gardenMatrix[yPosition + 1][xPosition] == 0){
							
							makeWalk(xPosition,yPosition + 1,'R',order,0,1);
							return;
						}
						
						if(this.gardenMatrix[yPosition - 1][xPosition] != 0 && this.gardenMatrix[yPosition + 1][xPosition] == 0) {
							
							makeWalk(xPosition,yPosition + 1,'R',order,0,1);
							return;
						}
						
						if(this.gardenMatrix[yPosition - 1][xPosition] == 0) {
							
							makeWalk(xPosition,yPosition - 1,'R',order,0,-1);
							return;
						}
					}
				}
			}
			
			
			// move right
			if(xChange == 1 && yChange == 0) {
				
				if(xPosition == this.xSize - 1) {
					
					return;
				}
				
				// direction is blocked
				if(this.gardenMatrix[yPosition][xPosition + 1] != 0) {
					
					// turn left
					if(flag == 'L') {
						
						if(yPosition == 0 && this.gardenMatrix[yPosition + 1][xPosition] == 0){
							
							makeWalk(xPosition,yPosition + 1,'L',order,0,1);
							return;
						}
						
						if(this.gardenMatrix[yPosition - 1][xPosition] != 0 && this.gardenMatrix[yPosition + 1][xPosition] == 0) {
							
							makeWalk(xPosition,yPosition + 1,'L',order,0,1);
							return;
						}
						
						if(this.gardenMatrix[yPosition - 1][xPosition] == 0) {
							
							makeWalk(xPosition,yPosition - 1,'L',order,0,-1);
							return;
						}
					}
					
					// turn right
					if(flag == 'R') {
						
						if(yPosition == this.ySize - 1 && this.gardenMatrix[yPosition - 1][xPosition] == 0){
							
							makeWalk(xPosition,yPosition - 1,'R',order,0,-1);
							return;
						}
						
						if(this.gardenMatrix[yPosition + 1][xPosition] != 0 && this.gardenMatrix[yPosition - 1][xPosition] == 0) {
							
							makeWalk(xPosition,yPosition - 1,'R',order,0,-1);
							return;
						}
						
						if(this.gardenMatrix[yPosition + 1][xPosition] == 0) {
							
							makeWalk(xPosition,yPosition + 1,'R',order,0,1);
							return;
						}
					}
				}
			}
			
			// make move
			xPosition = xPosition + xChange;
			yPosition = yPosition + yChange;
			
			// break when reaches garden borders
			if(xPosition == -1 || yPosition == -1 || xPosition == this.xSize || yPosition == this.ySize) {
				
				break;
			}
			
		}
	}
	
	
	/*
	 * Count and return fitness function
	 */
	public double getFitness() {
		
		int count = 0;
		
		for(int i=0; i<this.ySize; i++) {
			for(int j=0; j<this.xSize; j++) {
				
				if(this.gardenMatrix[i][j] != 0) {
					
					count++;
				}
			}
		}
		
		double fitness = (double)count / (xSize*ySize);
		
		return fitness;
	}
	
	
	/*
	 * Clear all moves from garden
	 */
	public void reset() {
		
		for(int i=0; i<this.ySize; i++) {
			for(int j=0; j<this.xSize; j++) {
				
				if(this.gardenMatrix[i][j] != -1) {
					
					this.gardenMatrix[i][j] = 0;
				}	
			}
		}
	}
	
	
	public int getGardenCircumference() {
		
		return 2*(this.xSize + this.ySize);
	}
	
	
	public String toString() {
		
		String str = "";
		
		for(int i=0; i<ySize; i++) {
			for(int j=0; j<xSize; j++) {
				
				str = str + gardenMatrix[i][j] + "\t";
			}
			str += "\n";
		}
		
		return str;
	}
	
	public int getXSize() {
		return xSize;
	}

	public int getYSize() {
		return ySize;
	}

	public int[][] getGardenMatrix() {
		return gardenMatrix;
	}

	public int getCountOfRocks() {
		return countOfRocks;
	}
}
