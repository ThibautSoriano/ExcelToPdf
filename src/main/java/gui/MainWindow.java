package main.java.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import main.java.utils.Internationalization;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame implements ActionListener{
    
    private List<SettingsChoicePanel> panels;
    private int currentPanel = 0;
    NavigationPanel np;
    
    public MainWindow() {
        panels = new LinkedList<SettingsChoicePanel>();
        panels.add(new MainWindowPanel());
        panels.add(new GeneralSettingsPanel());
        
        
        

        setSize(MainWindowZhengqin.WINDOW_WIDTH, MainWindowZhengqin.WINDOW_HEIGHT);
        getContentPane().setLayout(null);
        
        
        JLabel lblTitle = new JLabel(Internationalization.getKey("Excel to pdf converter"));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 33));
        lblTitle.setBounds(138, 11, 457, 34);
        getContentPane().add(lblTitle);

        np = new NavigationPanel(this);

        getContentPane().add(np);
        getContentPane().add(panels.get(0));
        
        addMenu();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        
        
                    try {
                            // set for file chooser look
                            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//                           
                    } catch (Exception e1) {
                            e1.printStackTrace();
                    }
                    
   
        
        new MainWindow();
       
    }
    
    
    public void showNextPanel(){
        
        if (currentPanel == panels.size()-1)
            return;
        
        getContentPane().remove(panels.get(currentPanel));
        currentPanel++;
        getContentPane().add(panels.get(currentPanel));
        
        if (currentPanel==0)
            np.hidePreviousButton();
        else
            np.showPreviousButton();
        
        repaint();
    }
    

    public void showPreviousPanel(){
        
        if (currentPanel == 0)
            return;
        
        getContentPane().remove(panels.get(currentPanel));
        currentPanel--;
        getContentPane().add(panels.get(currentPanel));
        
        if (currentPanel==0)
            np.hidePreviousButton();
        else
            np.showPreviousButton();
        
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Internationalization.getKey("Previous").equals(e.getActionCommand()))
                showPreviousPanel();
        else
            showNextPanel();
        
    }

   
    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);
        
        JMenuItem mntmExit = new JMenuItem("Exit");
        mnFile.add(mntmExit);
        
        JMenu mnSettings = new JMenu("Settings");
        menuBar.add(mnSettings);
        
        JMenu mnLanguage = new JMenu("Language");
        mnSettings.add(mnLanguage);
        
        JRadioButtonMenuItem rdbtnmntmEnglish = new JRadioButtonMenuItem("English");
        rdbtnmntmEnglish.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		// TODO
        	}
        });
        mnLanguage.add(rdbtnmntmEnglish);
        
        rdbtnmntmEnglish.setSelected(true);
        
        JRadioButtonMenuItem rdbtnmntmFranais = new JRadioButtonMenuItem("Fran\u00E7ais");
        rdbtnmntmFranais.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		// TODO
        	}
        });
        mnLanguage.add(rdbtnmntmFranais);
    }
}
