package main.java.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import main.java.utils.FileType;
import main.java.utils.Internationalization;
import main.java.utils.Language;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class MainWindow extends JFrame implements ActionListener{
    
    private List<SettingsChoicePanel> panels;
    private int currentPanel = 0;
    NavigationPanel np;
    
    private static final int WINDOW_HEIGHT = 500;
    
    private static final int WINDOW_WIDTH = 600;
    
    public MainWindow() {
        
        setBounds(200,100,WINDOW_WIDTH,WINDOW_HEIGHT);
        
        panels = new LinkedList<SettingsChoicePanel>();
        panels.add(new MainWindowPanel());
        panels.add(new GeneralSettingsPanel());
        panels.add(new TitleSettingsPanel());
        
        

        
        getContentPane().setLayout(null);
        
        
        

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
        		Internationalization.loadLanguage(Language.EN);
        	}
        });
        mnLanguage.add(rdbtnmntmEnglish);
        
        rdbtnmntmEnglish.setSelected(true);
        
        JRadioButtonMenuItem rdbtnmntmFranais = new JRadioButtonMenuItem("Fran\u00E7ais");
        rdbtnmntmFranais.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mousePressed(MouseEvent e) {
        	    
        	    System.out.println("Junior ce fils de pute");
        	    Internationalization.loadLanguage(Language.FR);
        	   
        	}
        });
        mnLanguage.add(rdbtnmntmFranais);
    }
    
    public  static  void openFileChooser(final FileType fileType,final JTextField field) {
        final JFrame frame = new JFrame(Internationalization.getKey("JFileChooser Popup"));
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
                	
                field.setText(selectedFile.getAbsolutePath());
               
                	
                
                
                frame.dispose();
              }
              else if (command.equals(JFileChooser.CANCEL_SELECTION)) {
            	  frame.dispose();
              }
            }
          };
          
          fileChooser.addActionListener(actionListener);

          frame.pack();
          frame.setVisible(true);
    }
}
