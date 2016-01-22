package sk.zatko.zens_garden.selection_strategy;

import sk.zatko.zens_garden.models.Garden;
import sk.zatko.zens_garden.models.Population;

/*
 * Interface for Selection methods
 * 
 * @pattern Strategy
 * 
 * @author Jozef Zatko
 */
public interface SelectionStrategy {

	public Population createNewPopulation(Population population, Garden garden);
}
