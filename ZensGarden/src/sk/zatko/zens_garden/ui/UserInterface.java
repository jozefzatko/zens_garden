package sk.zatko.zens_garden.ui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Toolkit;
import java.awt.Font;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JProgressBar;

/*
 * Swing JFrame window for main User Interface
 */
public class UserInterface extends JFrame {

	/**
	 * auto-generated UIDs
	 */
	private static final long serialVersionUID = -6508821919493349685L;

	private JPanel contentPane;

	private JSpinner populationSizeSpinner;
	private JSpinner eliteCountSpinner;
	private JSpinner startPositionChangeSpinner;
	private JSpinner behaviorChangeSpinner;
	private JSpinner genomeChangeSpinner;
	private JSpinner maxGenerationSpinner;
	
	private JComboBox<String> selectionStrategyComboBox;
	
	private JButton btnStart;
	
	private JLabel lblTournamentSize;
	private JSpinner tournamentSizeSpinner;

	private JProgressBar progressBar;

	public UserInterface() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("images\\alien_logo.png"));
		setResizable(false);
		setTitle("Zen's Garden");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1024, 600);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 222, 179));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMiddleImage = new JLabel("");
		lblMiddleImage.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMiddleImage.setIcon(new ImageIcon("images\\alien_big.png"));
		lblMiddleImage.setBounds(443, 101, 180, 432);
		contentPane.add(lblMiddleImage);
		
		JPanel northPanel = new JPanel();
		northPanel.setBounds(10, 11, 996, 144);
		contentPane.add(northPanel);
		northPanel.setLayout(null);
		
		JLabel lblTitleImage = new JLabel("");
		lblTitleImage.setFont(new Font("Comic Sans MS", Font.PLAIN, 99));
		lblTitleImage.setIcon(new ImageIcon("images\\zens_garden_title.png"));
		lblTitleImage.setBounds(0, 0, 996, 144);
		northPanel.add(lblTitleImage);
		
		
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(255, 250, 205));
		leftPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		leftPanel.setBounds(12, 168, 440, 341);
		contentPane.add(leftPanel);
		leftPanel.setLayout(null);
		
		JLabel lblPopulationSize = new JLabel("Population size");
		lblPopulationSize.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPopulationSize.setBounds(20, 30, 150, 18);
		leftPanel.add(lblPopulationSize);
		
		populationSizeSpinner = new JSpinner();
		populationSizeSpinner.setFont(new Font("Tahoma", Font.PLAIN, 16));
		populationSizeSpinner.setModel(new SpinnerNumberModel(100, 0, 1000, 10));
		populationSizeSpinner.setBounds(326, 20, 82, 38);
		leftPanel.add(populationSizeSpinner);
		
		JLabel lblEliteCount = new JLabel("Count of elitists");
		lblEliteCount.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEliteCount.setBounds(20, 80, 150, 18);
		leftPanel.add(lblEliteCount);
		
		eliteCountSpinner = new JSpinner();
		eliteCountSpinner.setFont(new Font("Tahoma", Font.PLAIN, 16));
		eliteCountSpinner.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		eliteCountSpinner.setBounds(326, 70, 82, 38);
		leftPanel.add(eliteCountSpinner);
		
		JLabel lblMutationProbability = new JLabel("Mutation probability [%]");
		lblMutationProbability.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMutationProbability.setBounds(20, 130, 240, 18);
		leftPanel.add(lblMutationProbability);
		
		JLabel lblStartPositionChange = new JLabel("- start position change");
		lblStartPositionChange.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblStartPositionChange.setBounds(30, 160, 230, 18);
		leftPanel.add(lblStartPositionChange);
		
		startPositionChangeSpinner = new JSpinner();
		startPositionChangeSpinner.setFont(new Font("Tahoma", Font.PLAIN, 16));
		startPositionChangeSpinner.setModel(new SpinnerNumberModel(3, 0, 100, 1));
		startPositionChangeSpinner.setBounds(326, 155, 72, 38);
		leftPanel.add(startPositionChangeSpinner);
		
		JLabel lblPerc1 = new JLabel("%");
		lblPerc1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPerc1.setBounds(402, 155, 20, 38);
		leftPanel.add(lblPerc1);
		
		JLabel lblBehaviorChange = new JLabel("- move direction change");
		lblBehaviorChange.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBehaviorChange.setBounds(30, 200, 230, 18);
		leftPanel.add(lblBehaviorChange);
		
		behaviorChangeSpinner = new JSpinner();
		behaviorChangeSpinner.setFont(new Font("Tahoma", Font.PLAIN, 16));
		behaviorChangeSpinner.setModel(new SpinnerNumberModel(3, 0, 100, 1));
		behaviorChangeSpinner.setBounds(326, 195, 72, 38);
		leftPanel.add(behaviorChangeSpinner);
		
		JLabel lblPerc2 = new JLabel("%");
		lblPerc2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPerc2.setBounds(402, 195, 20, 38);
		leftPanel.add(lblPerc2);
		
		JLabel lblGenomeChange = new JLabel("- random gene change");
		lblGenomeChange.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGenomeChange.setBounds(30, 240, 230, 18);
		leftPanel.add(lblGenomeChange);
		
		genomeChangeSpinner = new JSpinner();
		genomeChangeSpinner.setFont(new Font("Tahoma", Font.PLAIN, 16));
		genomeChangeSpinner.setModel(new SpinnerNumberModel(3, 0, 100, 1));
		genomeChangeSpinner.setBounds(326, 235, 72, 38);
		leftPanel.add(genomeChangeSpinner);
		
		JLabel lblPerc3 = new JLabel("%");
		lblPerc3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPerc3.setBounds(402, 235, 20, 38);
		leftPanel.add(lblPerc3);
		
		JLabel lblMaxGenerationCount = new JLabel("Max. generation count");
		lblMaxGenerationCount.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMaxGenerationCount.setBounds(20, 300, 230, 18);
		leftPanel.add(lblMaxGenerationCount);
		
		maxGenerationSpinner = new JSpinner();
		maxGenerationSpinner.setFont(new Font("Tahoma", Font.PLAIN, 16));
		maxGenerationSpinner.setModel(new SpinnerNumberModel(100, 0, 1000000, 100));
		maxGenerationSpinner.setBounds(326, 290, 82, 38);
		leftPanel.add(maxGenerationSpinner);
		
		
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(new Color(255, 250, 205));
		rightPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		rightPanel.setBounds(605, 168, 400, 341);
		contentPane.add(rightPanel);
		rightPanel.setLayout(null);
		
		JLabel lblSelectionMethod = new JLabel("Child genome selection method");
		lblSelectionMethod.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSelectionMethod.setBounds(20, 30, 367, 18);
		rightPanel.add(lblSelectionMethod);
		
		selectionStrategyComboBox = new JComboBox<String>();
		selectionStrategyComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		selectionStrategyComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Roulette Wheel Selection (Roulette1)", "Rank Selection (Roulette1)", "Tournament Selection"}));
		selectionStrategyComboBox.setBounds(20, 60, 357, 32);
		rightPanel.add(selectionStrategyComboBox);
		
		btnStart = new JButton("Start");
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnStart.setBounds(10, 290, 377, 38);
		rightPanel.add(btnStart);
		
		tournamentSizeSpinner = new JSpinner();
		tournamentSizeSpinner.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tournamentSizeSpinner.setModel(new SpinnerNumberModel(4, 1, 100, 1));
		tournamentSizeSpinner.setBounds(294, 115, 82, 38);
		tournamentSizeSpinner.setVisible(false);
		rightPanel.add(tournamentSizeSpinner);
		
		lblTournamentSize = new JLabel("- tournament size");
		lblTournamentSize.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTournamentSize.setBounds(40, 125, 140, 18);
		lblTournamentSize.setVisible(false);
		rightPanel.add(lblTournamentSize);
		
		progressBar = new JProgressBar();
		progressBar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		progressBar.setStringPainted(true);
		progressBar.setBounds(10, 522, 996, 30);
		contentPane.add(progressBar);
	}
	
	
	public JPanel getContentPane() {
		return contentPane;
	}

	public JSpinner getPopulationSizeSpinner() {
		return populationSizeSpinner;
	}

	public JSpinner getEliteCountSpinner() {
		return eliteCountSpinner;
	}

	public JSpinner getStartPositionChangeSpinner() {
		return startPositionChangeSpinner;
	}

	public JSpinner getBehaviorChangeSpinner() {
		return behaviorChangeSpinner;
	}

	public JSpinner getGenomeChangeSpinner() {
		return genomeChangeSpinner;
	}

	public JSpinner getMaxGenerationSpinner() {
		return maxGenerationSpinner;
	}

	public JComboBox<String> getSelectionStrategyComboBox() {
		return selectionStrategyComboBox;
	}

	public JButton getBtnStart() {
		return btnStart;
	}
	
	public JLabel getLblTournamentSize() {
		return lblTournamentSize;
	}

	public JSpinner getTournamentSizeSpinner() {
		return tournamentSizeSpinner;
	}
	
	public JProgressBar getProgressBar() {
		return progressBar;
	}
}
