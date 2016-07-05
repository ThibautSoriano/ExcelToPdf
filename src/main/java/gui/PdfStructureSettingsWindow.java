package main.java.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PdfStructureSettingsWindow {

	private JFrame frame;
	private JPanel panelDefineStructure;

	public JPanel getPanelDefineStructure() {
		return panelDefineStructure;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PdfStructureSettingsWindow window = new PdfStructureSettingsWindow();
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
	public PdfStructureSettingsWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(240, 240, 240));
		frame.getContentPane().setLayout(null);
		frame.setBounds(200, 100, 450, 300);
		frame.setSize(800, 500);
		
		JLabel lblDefineStructure = new JLabel("Define structure");
		lblDefineStructure.setBounds(419, 11, 130, 14);
		frame.getContentPane().add(lblDefineStructure);
		
		panelDefineStructure = new JPanel();
		panelDefineStructure.setBounds(110, 100, 560, 142);
		frame.getContentPane().add(panelDefineStructure);
		panelDefineStructure.setLayout(null);
		
		JLabel lblPageNumerotation = new JLabel("Page numerotation");
		lblPageNumerotation.setBounds(27, 34, 115, 19);
		panelDefineStructure.add(lblPageNumerotation);
		
		JRadioButton rdbtnBottomCenter = new JRadioButton("Bottom center");
		rdbtnBottomCenter.setBounds(157, 32, 109, 23);
		panelDefineStructure.add(rdbtnBottomCenter);
		rdbtnBottomCenter.setSelected(true);
		
		JRadioButton rdbtnBottomRight = new JRadioButton("Bottom right");
		rdbtnBottomRight.setBounds(319, 27, 109, 23);
		panelDefineStructure.add(rdbtnBottomRight);
		
		JCheckBox chckbxFooterLine = new JCheckBox("Separator line above footer");
		chckbxFooterLine.setSelected(true);
		chckbxFooterLine.setBounds(218, 82, 189, 23);
		panelDefineStructure.add(chckbxFooterLine);
		
		JCheckBox chckbxHeaderLine = new JCheckBox("Separator line below header");
		chckbxHeaderLine.setSelected(true);
		chckbxHeaderLine.setBounds(27, 82, 189, 23);
		panelDefineStructure.add(chckbxHeaderLine);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(27, 64, 397, 2);
		panelDefineStructure.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(27, 21, 397, 2);
		panelDefineStructure.add(separator_1);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
