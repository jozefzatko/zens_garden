package sk.zatko.zens_garden;

import sk.zatko.zens_garden.ui.ResultsGUI;
import sk.zatko.zens_garden.ui.UserInterface;

/*
 * Runs the application
 * Create GUI and controller objects
 * 
 * @author Jozef Zatko 
 */
public class Runner {

	public static void main(String[] args) {
	
		UserInterface gui = new UserInterface();
		ResultsGUI resultsGui = new ResultsGUI(); 
		
		Controller controller = new Controller(gui,resultsGui);
		controller.showGui();
	}
}
