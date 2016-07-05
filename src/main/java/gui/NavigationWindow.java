package main.java.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

public class NavigationWindow {

	private JFrame frame;
	//private JPanel panelNavigation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NavigationWindow window = new NavigationWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NavigationWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panelNavigation = new JPanel();
		panelNavigation.setLayout(null);
		panelNavigation.setBounds(41, 317, 716, 109);
		frame.getContentPane().add(panelNavigation);
		
		JButton btnNext = new JButton("Next");
		btnNext.setBounds(606, 43, 100, 23);
		panelNavigation.add(btnNext);
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.setBounds(32, 43, 100, 23);
		panelNavigation.add(btnPrevious);
	}

}
