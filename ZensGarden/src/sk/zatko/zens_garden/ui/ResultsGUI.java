package sk.zatko.zens_garden.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Toolkit;

/*
 * Swing JFrame window for results
 */
public class ResultsGUI extends JFrame {

	/**
	 * auto-generated UID
	 */
	private static final long serialVersionUID = 2194960311456271613L;

	private JPanel contentPane;

	private JTextArea textArea;
	
	public ResultsGUI() {
		
		setTitle("Results");
		setIconImage(Toolkit.getDefaultToolkit().getImage("images\\alien_logo.png"));
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(900, 45, 1024, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		textArea.setTabSize(4);
		textArea.setEditable(false);
		
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		scrollPane.setViewportView(textArea);
	}

	public void post(String msg) {
		
		this.textArea.append(msg);
	}
	
	public void reset() {
		
		this.textArea.setText("");
	}
}
