package main.java.gui.windowbuilder;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Color;

public class MainMenuWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MainMenuWindow window = new MainMenuWindow();
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
	public MainMenuWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(UIManager.getColor("TextArea.inactiveBackground"));
        panel.setBounds(0, 20, 584, 454);
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        
        JButton btnFromAnExcel = new JButton("From an excel file on your computer");
        btnFromAnExcel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        	}
        });
        btnFromAnExcel.setBounds(44, 241, 224, 51);
        panel.add(btnFromAnExcel);
        
        JButton btnByDownloadingDatas = new JButton("By downloading datas");
        btnByDownloadingDatas.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        	}
        });
        btnByDownloadingDatas.setBounds(311, 241, 224, 51);
        panel.add(btnByDownloadingDatas);
        
        JLabel lblChooseMode = new JLabel("Select how to import your files");
        lblChooseMode.setForeground(Color.BLACK);
        lblChooseMode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblChooseMode.setBounds(44, 130, 308, 67);
        panel.add(lblChooseMode);
        
        JLabel lblBackground = new JLabel("");
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
       
        lblBackground.setIcon(new ImageIcon(getClass().getResource("/background2.png")));
        lblBackground.setBounds(0, -21, 584, 463);
        panel.add(lblBackground);
	}
}
