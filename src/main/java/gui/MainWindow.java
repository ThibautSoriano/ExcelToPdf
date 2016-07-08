package main.java.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ButtonGroup;
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

import com.itextpdf.text.DocumentException;

import main.java.exceltopdf.ExcelToPdf;
import main.java.exceltopdf.HeaderFooter;
import main.java.exceltopdf.pdfsections.InsertPage;
import main.java.exceltopdf.pdfsections.Section;
import main.java.exceltopdf.pdfsections.TitlePage;
import main.java.utils.FileType;
import main.java.utils.Internationalization;
import main.java.utils.Language;
import main.java.utils.Utils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class MainWindow extends JFrame implements INavigation{
    
    private List<SettingsChoicePanel> panels;
    private int currentPanel = 0;
    NavigationPanel np;
    
    private static final int WINDOW_HEIGHT = 500;
    
    private static final int WINDOW_WIDTH = 600;
    
    public MainWindow() {
        
        setResizable(false);
        
        setBounds(200,100,WINDOW_WIDTH,WINDOW_HEIGHT);
        
        getContentPane().setLayout(null);
        
        createPanels(0);
        
        np = new NavigationPanel(panels.size(),1);
        np.addNavigationListener(this);
        getContentPane().add(np);
        
        
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

        getContentPane().remove(panels.get(currentPanel));
        currentPanel++;
        getContentPane().add(panels.get(currentPanel));
        

        repaint();
        setVisible(true);
    }
    

    public void showPreviousPanel(){

        getContentPane().remove(panels.get(currentPanel));
        currentPanel--;
        getContentPane().add(panels.get(currentPanel));
        
       
        repaint();
        setVisible(true);
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
        
        
        ButtonGroup g = new ButtonGroup();
        
        for (Language lang : Language.values()) {
            
            JRadioButtonMenuItem rdbtnmntm = new JRadioButtonMenuItem(lang.getName());
            rdbtnmntm.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                        Internationalization.loadLanguage(lang);
                        
                        getContentPane().remove(panels.get(currentPanel));
                        createPanels(currentPanel);
                        
                        getContentPane().remove(np);
                        np = np.getNewInstance();
                        getContentPane().add(np);
                        
                        rdbtnmntm.setSelected(true);
                        repaint();
                        setVisible(true);
                }
            });
            
            if (lang == Language.EN)
                rdbtnmntm.setSelected(true);
            
            g.add(rdbtnmntm);
            mnLanguage.add(rdbtnmntm);
        }
        
        
        
        
        
        
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

    @Override
    public void next() {
        if (currentPanel == panels.size()-1)
            return;
        showNextPanel();
        
        
    }

    @Override
    public void previous() {
        if (currentPanel == 0)
            return;
       showPreviousPanel();
        
    }

    @Override
    public void validation()  {
        
        ExcelToPdf etpd = new ExcelToPdf();
        List<Section> sections = new ArrayList<Section>();
        
        
        GeneralSettingsPanel gsp = (GeneralSettingsPanel) panels.get(1);
        
        
        
        TitlePage tp = new TitlePage();
        
        
        TitleSettingsPanel tsp = (TitleSettingsPanel) panels.get(2);
        HeaderFooter hfTitle = new HeaderFooter(tsp.getChckbxHeader().isSelected(),tsp.getChckbxSeparatorLineBelowHeader().isSelected(),
                tsp.getChckbxFooter().isSelected(),tsp.getChckbxSeparatorLineAboveFooter().isSelected(),false);
        hfTitle.setLineInFooter(gsp.getTxtBottomLeftText().getText());
        tp.setStructure(hfTitle);
        tp.setBelowTitle(tsp.getTxtrBelowTitle().getText());
        sections.add(tp);
        
        
        
        InsertPage ip = new InsertPage();
        InsertPageSettingsPanel isp = (InsertPageSettingsPanel) panels.get(3);
        HeaderFooter hfInsert = new HeaderFooter(false, false, false, false, false);
        hfInsert.setLineInFooter("dias");
        ip.setCustomTextArea(isp.getTxtrCreatedBy().getText());
        ip.setStructure(hfInsert);
        
        
        sections.add(ip);
        try {
            etpd.createPdf(ExcelToPdf.SRC,"meruguez.pdf", sections);
        } catch (IOException | DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
    }
    
    private void createPanels(int panelToShow) {
       
        panels = new LinkedList<SettingsChoicePanel>();
        panels.add(new MainWindowPanel());
        panels.add(new GeneralSettingsPanel());
        panels.add(new TitleSettingsPanel());
        panels.add(new InsertPageSettingsPanel());
        panels.add(new ColumnsSettingsPanel());
        
        
        getContentPane().add(panels.get(panelToShow));
    }
}
