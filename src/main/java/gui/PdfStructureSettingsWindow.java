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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.java.utils.FileType;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PdfStructureSettingsWindow {

	private JFrame frame;
	private JPanel panelDefineStructure;
	private JTextField txtWebsite;
	private JTextField txtLogo;
	private JTextField txtBottomLeftText;

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
		frame.setSize(600, 500);
		
		panelDefineStructure = new JPanel();
		panelDefineStructure.setBounds(10, 59, 560, 318);
		frame.getContentPane().add(panelDefineStructure);
		panelDefineStructure.setLayout(null);
		
		JLabel lblPageNumerotation = new JLabel("Page numerotation");
		lblPageNumerotation.setBounds(64, 29, 115, 19);
		panelDefineStructure.add(lblPageNumerotation);
		
		JRadioButton rdbtnBottomCenter = new JRadioButton("Bottom center");
		rdbtnBottomCenter.setBounds(194, 27, 109, 23);
		panelDefineStructure.add(rdbtnBottomCenter);
		rdbtnBottomCenter.setSelected(true);
		
		JRadioButton rdbtnBottomRight = new JRadioButton("Bottom right");
		rdbtnBottomRight.setBounds(356, 22, 109, 23);
		panelDefineStructure.add(rdbtnBottomRight);
		
		ButtonGroup pagesNumGroup = new ButtonGroup();
		pagesNumGroup.add(rdbtnBottomRight);
		pagesNumGroup.add(rdbtnBottomCenter);
		
		JCheckBox chckbxFooterLine = new JCheckBox("Separator line above footer");
		chckbxFooterLine.setSelected(true);
		chckbxFooterLine.setBounds(255, 223, 189, 23);
		panelDefineStructure.add(chckbxFooterLine);
		
		JCheckBox chckbxHeaderLine = new JCheckBox("Separator line below header");
		chckbxHeaderLine.setSelected(true);
		chckbxHeaderLine.setBounds(255, 73, 189, 23);
		panelDefineStructure.add(chckbxHeaderLine);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(64, 59, 397, 2);
		panelDefineStructure.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(64, 11, 397, 2);
		panelDefineStructure.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(64, 211, 397, 2);
		panelDefineStructure.add(separator_2);
		
		JLabel lblYourCompanyWebsite = new JLabel("Your company website");
		lblYourCompanyWebsite.setBounds(64, 119, 159, 19);
		panelDefineStructure.add(lblYourCompanyWebsite);
		
		txtWebsite = new JTextField();
		txtWebsite.setText("www.gemius.hu");
		txtWebsite.setColumns(10);
		txtWebsite.setBounds(255, 118, 177, 20);
		panelDefineStructure.add(txtWebsite);
		
		JCheckBox chckbxHeader = new JCheckBox("Header");
		chckbxHeader.setSelected(true);
		chckbxHeader.setBounds(64, 73, 189, 23);
		panelDefineStructure.add(chckbxHeader);
		
		JCheckBox chckbxFooter = new JCheckBox("Footer");
		chckbxFooter.setSelected(true);
		chckbxFooter.setBounds(64, 223, 189, 23);
		panelDefineStructure.add(chckbxFooter);
		
		JButton btnBrowse = new JButton("");
		btnBrowse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				MainWindow.openFileChooser(FileType.LOGO, txtLogo);
			}
		});
		btnBrowse.setIcon(new ImageIcon(".\\src\\main\\resources\\Browse.png"));
		btnBrowse.setBounds(442, 161, 25, 25);
		btnBrowse.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelDefineStructure.add(btnBrowse);
		
		txtLogo = new JTextField();
		txtLogo.setColumns(10);
		txtLogo.setBounds(255, 164, 177, 20);
		panelDefineStructure.add(txtLogo);
		
		JLabel lblYourCompanyLogo = new JLabel("Your company logo");
		lblYourCompanyLogo.setBounds(64, 163, 168, 22);
		panelDefineStructure.add(lblYourCompanyLogo);
		
		JLabel lblBottomLeftText = new JLabel("Bottom left text");
		lblBottomLeftText.setBounds(64, 272, 159, 19);
		panelDefineStructure.add(lblBottomLeftText);
		
		txtBottomLeftText = new JTextField();
		txtBottomLeftText.setColumns(10);
		txtBottomLeftText.setBounds(255, 271, 177, 20);
		panelDefineStructure.add(txtBottomLeftText);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(64, 305, 397, 2);
		panelDefineStructure.add(separator_3);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
