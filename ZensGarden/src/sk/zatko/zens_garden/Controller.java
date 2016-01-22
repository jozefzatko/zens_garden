package sk.zatko.zens_garden;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import sk.zatko.zens_garden.models.Solver;
import sk.zatko.zens_garden.ui.ResultsGUI;
import sk.zatko.zens_garden.ui.UserInterface;

/*
 * Application Controller
 * 
 * Handle GUI Listeners
 * Starts and stops Solver threads
 * 
 * @author Jozef Zatko
 */
public class Controller {

	private UserInterface gui;
	private ResultsGUI resultsGui;
	
	private Solver solver;
	
	private Thread solverThread;
	
	
	/*
	 * Controller constructor - initialize GUI attributes and listeners
	 */
	public Controller(UserInterface gui, ResultsGUI resultsGui) {
		
		this.gui = gui;
		this.resultsGui = resultsGui;
		
		this.gui.getBtnStart().addActionListener(new StartListener());
		this.gui.getPopulationSizeSpinner().addChangeListener(new SpinnerChangeListener());
		this.gui.getSelectionStrategyComboBox().addActionListener(new ChangeSelectionMethosListener());
	}

	
	public void showGui() {
	
		gui.setVisible(true);
	}
	
	
	/*
	 * Start / Stop button listener
	 */
	class StartListener implements ActionListener {

		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent event) {
			
			resultsGui.reset();
			
			if(gui.getBtnStart().getText().equals("Start")) {
				
				gui.getBtnStart().setText("Stop");
				
				solver = new Solver(gui,resultsGui); 
				solverThread = new Thread(solver);
				solverThread.start();
					
			}
			else {
				
				gui.getBtnStart().setText("Start");
				
				solverThread.stop();
			}
		}
	}
	
	
	/*
	 * Selection method ComboBox listener
	 */
	class ChangeSelectionMethosListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if(gui.getSelectionStrategyComboBox().getSelectedIndex() == 2) {
				
				gui.getLblTournamentSize().setVisible(true);
				gui.getTournamentSizeSpinner().setVisible(true);
			}
			else {
				
				gui.getLblTournamentSize().setVisible(false);
				gui.getTournamentSizeSpinner().setVisible(false);
			}
		}
	 }
	 
	 
	/*
	 * Elitism count spinner listeners
	 */
	 class SpinnerChangeListener implements ChangeListener {

		public void stateChanged(ChangeEvent event) {

			gui.getEliteCountSpinner().setModel(new SpinnerNumberModel(0, 0, (int)gui.getPopulationSizeSpinner().getValue(), 1));
		}		 
	}
}
