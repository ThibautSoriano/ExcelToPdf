package main.java.gui.windowbuilder;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class InsertPageSettingsWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertPageSettingsWindow window = new InsertPageSettingsWindow();
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
	public InsertPageSettingsWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setType(Type.UTILITY);
		frame.setBounds(100, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 20, 584, 380);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JCheckBox chckbxSeparatorLineAbove = new JCheckBox("Separator line above footer");
		chckbxSeparatorLineAbove.setSelected(true);
		chckbxSeparatorLineAbove.setBounds(284, 143, 189, 23);
		panel.add(chckbxSeparatorLineAbove);
		
		JCheckBox chckbxSeparatorLineBelow = new JCheckBox("Separator line below header");
		chckbxSeparatorLineBelow.setSelected(true);
		chckbxSeparatorLineBelow.setBounds(284, 71, 189, 23);
		panel.add(chckbxSeparatorLineBelow);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(93, 57, 397, 2);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(93, 111, 397, 2);
		panel.add(separator_1);
		
		JCheckBox chckbxHeader = new JCheckBox("Header");
		chckbxHeader.setBounds(93, 71, 189, 23);
		panel.add(chckbxHeader);
		
		JCheckBox chckbxFooter = new JCheckBox("Footer");
		chckbxFooter.setBounds(93, 143, 189, 23);
		panel.add(chckbxFooter);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(93, 189, 397, 2);
		panel.add(separator_2);
		
		JLabel lblCustomTextArea = new JLabel("Custom text area");
		lblCustomTextArea.setBounds(93, 212, 127, 19);
		panel.add(lblCustomTextArea);
		
		JTextArea txtrCreatedBy = new JTextArea();
		txtrCreatedBy.setWrapStyleWord(true);
		txtrCreatedBy.setText("k\u00E9sz\u00EDtette :");
		txtrCreatedBy.setLineWrap(true);
		txtrCreatedBy.setBounds(239, 212, 241, 69);
		panel.add(txtrCreatedBy);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(93, 299, 397, 2);
		panel.add(separator_3);
	}

}
