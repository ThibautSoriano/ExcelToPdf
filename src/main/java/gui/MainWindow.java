package main.java.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import main.java.exceltopdf.ExcelToPdf;
import main.java.utils.FileType;
import main.java.utils.Utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class MainWindow {

	private JFrame frmConverter;
	private JTextField txtExcel;
	private JTextField txtLogo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// set for file chooser look
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
				try {
					MainWindow window = new MainWindow();
					window.frmConverter.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConverter = new JFrame();
		frmConverter.setTitle("Converter");
		frmConverter.getContentPane().setBackground(new Color(0, 206, 209));
		frmConverter.setBackground(Color.WHITE);
		frmConverter.setBounds(200, 100, 450, 300);
		frmConverter.setSize(800, 500);
		frmConverter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConverter.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("Excel to pdf converter");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Gloucester MT Extra Condensed", Font.BOLD, 33));
		lblTitle.setBounds(246, 11, 270, 34);
		frmConverter.getContentPane().add(lblTitle);
		
		JLabel lblChooseExcel = new JLabel("Choose an excel file");
		lblChooseExcel.setFont(new Font("Gloucester MT Extra Condensed", Font.PLAIN, 20));
		lblChooseExcel.setBounds(50, 97, 143, 22);
		frmConverter.getContentPane().add(lblChooseExcel);
		
		JLabel lblLogo = new JLabel("Specify your logo (if you want)");
		lblLogo.setFont(new Font("Gloucester MT Extra Condensed", Font.PLAIN, 20));
		lblLogo.setBounds(50, 167, 177, 22);
		frmConverter.getContentPane().add(lblLogo);
		
		txtExcel = new JTextField();
		txtExcel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtExcel.setBounds(244, 101, 380, 20);
		frmConverter.getContentPane().add(txtExcel);
		txtExcel.setColumns(10);
		
		txtLogo = new JTextField();
		txtLogo.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtLogo.setColumns(10);
		txtLogo.setBounds(246, 171, 378, 20);
		frmConverter.getContentPane().add(txtLogo);
		
		JButton btnExcel = new JButton("Browse");
		btnExcel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openFileChooser(FileType.EXCEL);
			}
		});
		btnExcel.setFont(new Font("Gloucester MT Extra Condensed", Font.PLAIN, 20));
		btnExcel.setBounds(664, 98, 89, 23);
		frmConverter.getContentPane().add(btnExcel);
		
		JButton btnLogo = new JButton("Browse");
		btnLogo.setFont(new Font("Gloucester MT Extra Condensed", Font.PLAIN, 20));
		btnLogo.setBounds(664, 168, 89, 23);
		btnLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openFileChooser(FileType.LOGO);
			}
		});
		frmConverter.getContentPane().add(btnLogo);
		
		JButton btnGo = new JButton("Go !");
		btnGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ExcelToPdf excelToPdf = new ExcelToPdf();
	               try {
	            	   if (!Utils.isXlsExension(txtExcel.getText())) {
	            		   JOptionPane.showMessageDialog(null, "Please put an Excel file as input (.xls)", "Bad input file", JOptionPane.ERROR_MESSAGE);
	            	   }
	            	   else {
	            		   excelToPdf.createPdf(txtExcel.getText(), Utils.changeExtension(txtExcel.getText()), txtLogo.getText());
	            	   }
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnGo.setFont(new Font("Gloucester MT Extra Condensed", Font.PLAIN, 20));
		btnGo.setBounds(299, 254, 89, 23);
		frmConverter.getContentPane().add(btnGo);
	}
	
	
	
	
	
	public void openFileChooser(final FileType fileType) {
        final JFrame frame = new JFrame("JFileChooser Popup");
        Container contentPane = frame.getContentPane();
        
        JFileChooser fileChooser = new JFileChooser(".");
        fileChooser.setControlButtonsAreShown(true);
        contentPane.add(fileChooser, BorderLayout.CENTER);
        
        
        fileChooser.setAcceptAllFileFilterUsed(false);
        
        
        FileFilter filter = new FileNameExtensionFilter(fileType.getDescription(), fileType.getAcceptedExtensions());
        fileChooser.setFileFilter(filter);
        
        
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
              JFileChooser theFileChooser = (JFileChooser) actionEvent.getSource();
              String command = actionEvent.getActionCommand();
              if (command.equals(JFileChooser.APPROVE_SELECTION)) {
                File selectedFile = theFileChooser.getSelectedFile();
                if (fileType == FileType.EXCEL) {
                	txtExcel.setText(selectedFile.getAbsolutePath());
                }
                else if (fileType == FileType.LOGO) {
                	txtLogo.setText(selectedFile.getAbsolutePath());
                }
                
                frame.dispose();
              }
            }
          };
          
          fileChooser.addActionListener(actionListener);

          frame.pack();
          frame.setVisible(true);
    }
}
