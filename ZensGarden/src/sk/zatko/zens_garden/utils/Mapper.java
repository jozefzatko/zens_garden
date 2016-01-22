package sk.zatko.zens_garden.utils;

import sk.zatko.zens_garden.models.Garden;

/*
 * Map genome information into garden position
 * 
 * @author Jozef Zatko
 */
public class Mapper {

	private Garden garden;
	
	
	public Mapper(Garden garden) {
		
		this.garden = garden;
	}
	
	
	/*
	 * Get X coordinate of garden based on genome value
	 */
	public int getXValue(int value) {
		
		if(value <= garden.getXSize()) {
			
			return value - 1;
		}
		
		if(value >= garden.getXSize() + 1 && value <= garden.getXSize() + garden.getYSize()) {
			
			return garden.getXSize() - 1;
		}
		
		if(value >= garden.getXSize() + garden.getYSize() + 1 && value <= 2*garden.getXSize() + garden.getYSize()) {
			
			return 2*garden.getXSize() + garden.getYSize() - value;
		}
		
		return 0;
	}
	
	
	/*
	 * Get Y coordinate of garden based on genome value
	 */
	public int getYValue(int value) {
		
		if(value <= garden.getXSize()) {
			
			return 0;
		}
		
		if(value >= garden.getXSize() + 1 && value <= garden.getXSize() + garden.getYSize()) {
			
			return value - garden.getXSize() - 1;
		}
		
		if(value >= garden.getXSize() + garden.getYSize() + 1 && value <= 2*garden.getXSize() + garden.getYSize()) {
			
			return garden.getYSize() - 1;
		}
		
		return 2*garden.getXSize() + 2*garden.getYSize() - value;
	}
	
	
	/*
	 * Find out whether move through garden is vertical
	 */
	public boolean isVertical(int value) {
		
		if(value <= garden.getXSize()) {
			
			return true;
		}
		
		if(value >= garden.getXSize() + garden.getYSize() + 1 && value <= 2*garden.getXSize() + garden.getYSize()) {
			
			return true;
		}
		
		return false;
	}
	
	
	/*
	 * Find out whether move through garden is vertical
	 */
	public boolean isHorizontal(int value) {
		
		if(value >= garden.getXSize() + 1 && value <= garden.getXSize() + garden.getYSize()) {
			
			return true;
		}
		
		if(value >= 2*garden.getXSize() + garden.getYSize() + 1) {
			
			return true;
		}
		
		return false;
	}
}
