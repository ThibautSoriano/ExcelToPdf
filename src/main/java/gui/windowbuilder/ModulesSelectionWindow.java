package main.java.gui.windowbuilder;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.java.gui.MainWindow;
import main.java.utils.FileType;
import main.java.utils.Language;

import javax.swing.JComboBox;

public class ModulesSelectionWindow {

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
					ModulesSelectionWindow window = new ModulesSelectionWindow();
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
	public ModulesSelectionWindow() {
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
		frame.setSize(600, 500);
		
		panelDefineStructure = new JPanel();
		panelDefineStructure.setBounds(0, 20, 584, 380);
		frame.getContentPane().add(panelDefineStructure);
		panelDefineStructure.setLayout(null);
		
		ButtonGroup pagesNumGroup = new ButtonGroup();
		
		JCheckBox chckbxSummary = new JCheckBox("Summary");
		chckbxSummary.setSelected(true);
		chckbxSummary.setBounds(93, 93, 189, 23);
		panelDefineStructure.add(chckbxSummary);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(93, 57, 397, 2);
		panelDefineStructure.add(separator_1);
		
		JCheckBox chckbxRankings = new JCheckBox("Rankings");
		chckbxRankings.setSelected(true);
		chckbxRankings.setBounds(93, 182, 189, 23);
		panelDefineStructure.add(chckbxRankings);
		
		JCheckBox chckbxTechnical = new JCheckBox("Technical");
		chckbxTechnical.setSelected(true);
		chckbxTechnical.setBounds(313, 182, 189, 23);
		panelDefineStructure.add(chckbxTechnical);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(93, 360, 397, 2);
		panelDefineStructure.add(separator_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(313, 295, 177, 23);
		panelDefineStructure.add(comboBox);
		
		for (Language lang : Language.values()) {
                    comboBox.addItem(lang);
                }
		
		
		JLabel lblChoosePdfOutput = new JLabel("Choose pdf output language");
		lblChoosePdfOutput.setBounds(93, 295, 199, 23);
		panelDefineStructure.add(lblChoosePdfOutput);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
