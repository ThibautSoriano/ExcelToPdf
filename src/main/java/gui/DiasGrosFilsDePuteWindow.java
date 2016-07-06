package main.java.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class DiasGrosFilsDePuteWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiasGrosFilsDePuteWindow window = new DiasGrosFilsDePuteWindow();
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
	public DiasGrosFilsDePuteWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(300, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 20, 584, 380);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JCheckBox checkBox = new JCheckBox("Separator line above footer");
		checkBox.setSelected(true);
		checkBox.setBounds(284, 143, 189, 23);
		panel.add(checkBox);
		
		JCheckBox checkBox_1 = new JCheckBox("Separator line below header");
		checkBox_1.setSelected(true);
		checkBox_1.setBounds(284, 71, 189, 23);
		panel.add(checkBox_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(93, 57, 397, 2);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(93, 111, 397, 2);
		panel.add(separator_1);
		
		JCheckBox checkBox_2 = new JCheckBox("Header");
		checkBox_2.setBounds(93, 71, 189, 23);
		panel.add(checkBox_2);
		
		JCheckBox checkBox_3 = new JCheckBox("Footer");
		checkBox_3.setBounds(93, 143, 189, 23);
		panel.add(checkBox_3);
		
		JLabel lblTextBelowTitle = new JLabel("Text below title");
		lblTextBelowTitle.setBounds(93, 210, 159, 19);
		panel.add(lblTextBelowTitle);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(93, 189, 397, 2);
		panel.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(93, 297, 397, 2);
		panel.add(separator_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(238, 209, 243, 71);
		panel.add(scrollPane);
		
		
		JTextArea txtrBelowTitle = new JTextArea();
		scrollPane.setViewportView(txtrBelowTitle);
		txtrBelowTitle.setText("Online kamp\u00E1ny elemz\u00E9se");
		txtrBelowTitle.setWrapStyleWord(true);
		txtrBelowTitle.setLineWrap(true);
		
		
		
		
	}
}
