package main.java.gui;

import java.awt.EventQueue;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

public class NavigationWindow {

	private JFrame frame;
	private JPanel panelNavigation;
	private JButton btnNext;
	private JButton btnPrevious;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					/* Linked List Declaration */
			         LinkedList<String> linkedlist = new LinkedList<String>();

			         /*add(String Element) is used for adding 
			          * the elements to the linked list*/
			         linkedlist.add("Item1");
			         linkedlist.add("Item5");
			         linkedlist.add("Item3");
			         linkedlist.add("Item6");
			         linkedlist.add("Item2");
			         System.out.println("Linked List Content: " +linkedlist);
			         
			         linkedlist.addFirst("First Item");
			         linkedlist.addLast("Last Item");
			         System.out.println("LinkedList Content after addition: " +linkedlist);

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
		
		panelNavigation = new JPanel();
		panelNavigation.setLayout(null);
		panelNavigation.setBounds(41, 317, 716, 109);
		frame.getContentPane().add(panelNavigation);
		
		btnNext = new JButton("Next");
		btnNext.setBounds(606, 43, 100, 23);
		panelNavigation.add(btnNext);
		
		btnPrevious = new JButton("Previous");
		btnPrevious.setBounds(32, 43, 100, 23);
		panelNavigation.add(btnPrevious);
	}

	public JPanel getPanelNavigation() {
		return panelNavigation;
	}

	public JButton getBtnNext() {
		return btnNext;
	}

	public JButton getBtnPrevious() {
		return btnPrevious;
	}

}
