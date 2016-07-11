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

import main.java.excelreader.ExcelReader;
import main.java.excelreader.ExcelReaderRankings;
import main.java.excelreader.ExcelReaderTechnical;
import main.java.exceltopdf.ExcelToPdf;
import main.java.exceltopdf.HeaderFooter;
import main.java.exceltopdf.pdfsections.ContentPage;
import main.java.exceltopdf.pdfsections.InsertPage;
import main.java.exceltopdf.pdfsections.Section;
import main.java.exceltopdf.pdfsections.TitlePage;
import main.java.utils.FileType;
import main.java.utils.Internationalization;
import main.java.utils.Language;
import main.java.utils.Message;
import main.java.utils.Utils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class MainWindow extends JFrame implements IMainFrame {

    private List<SettingsChoicePanel> panels;
    private int currentPanel = 0;
    NavigationPanel np;

    private static final int WINDOW_HEIGHT = 500;

    private static final int WINDOW_WIDTH = 600;

    public MainWindow() {

        setResizable(false);

        setBounds(200, 100, WINDOW_WIDTH, WINDOW_HEIGHT);

        getContentPane().setLayout(null);

        createPanels(0);

        np = new NavigationPanel(this);
        np.hidePreviousButton();
        
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

    public void showNextPanel() {

        getContentPane().remove(panels.get(currentPanel));
        currentPanel++;
        getContentPane().add(panels.get(currentPanel));

        repaint();
        setVisible(true);
    }

    public void showPreviousPanel() {

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

        for (final Language lang : Language.values()) {

            final JRadioButtonMenuItem rdbtnmntm = new JRadioButtonMenuItem(
                    lang.getName());
            rdbtnmntm.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    Internationalization.loadLanguage(lang);

                    getContentPane().remove(panels.get(currentPanel));
                    createPanels(currentPanel);

                    getContentPane().remove(np);
                    np = np.getNewInstance();
                    
                    if (currentPanel == 0)
                        np.hidePreviousButton();
                    else if (currentPanel == panels.size() -1)
                        np.showValidateButton();
                    
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

    public static void openFileChooser(final FileType fileType,
            final JTextField field) {
        final JFrame frame = new JFrame(
                Internationalization.getKey("JFileChooser Popup"));
        Container contentPane = frame.getContentPane();

        JFileChooser fileChooser = new JFileChooser(".");
        fileChooser.setControlButtonsAreShown(true);
        contentPane.add(fileChooser, BorderLayout.CENTER);

        fileChooser.setAcceptAllFileFilterUsed(false);

        FileFilter filter = new FileNameExtensionFilter(
                fileType.getDescription(), fileType.getAcceptedExtensions());
        fileChooser.setFileFilter(filter);

        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser theFileChooser = (JFileChooser) actionEvent
                        .getSource();
                String command = actionEvent.getActionCommand();
                if (command.equals(JFileChooser.APPROVE_SELECTION)) {
                    File selectedFile = theFileChooser.getSelectedFile();

                    field.setText(selectedFile.getAbsolutePath());

                    frame.dispose();
                } else if (command.equals(JFileChooser.CANCEL_SELECTION)) {
                    frame.dispose();
                }
            }
        };

        fileChooser.addActionListener(actionListener);

        frame.pack();
        frame.setVisible(true);
    }


     @Override
    public void validation() {

        ExcelToPdf etpd = new ExcelToPdf();
        List<Section> sections = new ArrayList<Section>();

        MainWindowPanel mwp = (MainWindowPanel) panels.get(0);
        GeneralSettingsPanel gsp = (GeneralSettingsPanel) panels.get(1);
        TitleSettingsPanel tsp = (TitleSettingsPanel) panels.get(2);
        InsertPageSettingsPanel isp = (InsertPageSettingsPanel) panels.get(3);
        ColumnsSettingsPanel csp = (ColumnsSettingsPanel) panels.get(4);
        
        
        int positionPageCount = gsp.getRdbtnBottomCenter().isSelected() ? HeaderFooter.PAGE_COUNT_MIDDLE : HeaderFooter.PAGE_COUNT_RIGHT; 
        

        // Infos for the title page
        TitlePage tp = new TitlePage();

        HeaderFooter hfTitle = new HeaderFooter(
                tsp.getChckbxHeader().isSelected(),
                tsp.getChckbxSeparatorLineBelowHeader().isSelected(),
                tsp.getChckbxFooter().isSelected(),
                tsp.getChckbxSeparatorLineAboveFooter().isSelected(), positionPageCount);

        hfTitle.setLineInFooter(gsp.getTxtBottomLeftText().getText());
        hfTitle.setLineInHeader(gsp.getTxtWebsite().getText());
        hfTitle.setLogoInHeader(gsp.getTxtLogo().getText());
        tp.setStructure(hfTitle);
        tp.setBelowTitle(tsp.getTxtrBelowTitle().getText());
        sections.add(tp);

        
            
       
        
        // Infos for the insert page
        InsertPage ip = new InsertPage();

        HeaderFooter hfInsert = new HeaderFooter(
                isp.getChckbxHeader().isSelected(),
                isp.getChckbxSeparatorLineBelow().isSelected(),
                isp.getChckbxFooter().isSelected(),
                isp.getChckbxSeparatorLineAbove().isSelected(),positionPageCount );

        hfInsert.setLineInHeader(gsp.getTxtWebsite().getText());
        hfInsert.setLineInFooter(gsp.getTxtBottomLeftText().getText());
        hfInsert.setLogoInHeader(gsp.getTxtLogo().getText());

        ip.setCustomTextArea(isp.getTxtrCreatedBy().getText());
        ip.setStructure(hfInsert);

        sections.add(ip);

        // Columns choice settings
        List<String> excelPaths = new ArrayList<String>();
        List<JTextField> fields = mwp.getFields();
        
        int i = 0;
        for (JTextField jTextField : fields) {
            
            if (++i == 2)
                break;
            
            String src = jTextField.getText();
            if (!src.isEmpty()) {
                ContentPage contentPage = null;
                ExcelReader excelReader = null;
                
                if (src.contains("Rankings")) {
                    excelReader = new ExcelReaderRankings();
                    contentPage = new ContentPage(
                            csp.getChckbxImpressionsRankings().isSelected(),
                            csp.getChckbxUniqueCookiesRankings().isSelected(),
                            csp.getChckbxFrequencyTechnical().isSelected(),
                            csp.getChckbxClicksRankings().isSelected(),
                            csp.getChckbxClickingUsersRankings().isSelected(),
                            csp.getChckbxClickThroughRateRankings().isSelected(),
                            csp.getChckbxUniqueCTRTechnical().isSelected());
                }
                else if (src.contains("Technical")) {
                    excelReader = new ExcelReaderTechnical();
                     contentPage = new ContentPage(
                            csp.getChckbxImpressionsTechnical().isSelected(),
                            csp.getChckbxUniqueCookiesTechnical().isSelected(),
                            csp.getChckbxFrequencyTechnical().isSelected(),
                            csp.getChckbxClicksTechnical().isSelected(),
                            csp.getChckbxClickingUsersTechnical().isSelected(),
                            csp.getChckbxClickThroughRateTechnical().isSelected(),
                            csp.getChckbxUniqueCTRTechnical().isSelected());
                    
                }
                else {
                    System.err.println("xls not recognized");
                    return;
                }
                
                excelPaths.add(src);

                HeaderFooter hfContent = new HeaderFooter(
                        gsp.getChckbxHeader().isSelected(),
                        gsp.getChckbxFooterLine().isSelected(),
                        gsp.getChckbxFooter().isSelected(),
                        gsp.getChckbxHeaderLine().isSelected(),positionPageCount);
                hfContent.setLineInHeader(gsp.getTxtWebsite().getText());
                hfContent.setLineInFooter(gsp.getTxtBottomLeftText().getText());
                hfContent.setLogoInHeader(gsp.getTxtLogo().getText());
                contentPage.setStructure(hfContent);
                sections.add(contentPage);
            }
        }
        

        try {
            etpd.createPdf(excelPaths, "meruguez.pdf",
                    sections);
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

    @Override
    public void nextPanel() {
        Message m = new Message(); 
        if (panels.get(currentPanel).isEveryThingOk(m)) {
            
            np.showPreviousButton();
            showNextPanel();
            if (currentPanel == panels.size()-1) {
                np.showValidateButton();

            }
            
        }
        else 
            System.out.println(m.getMessages());
        
    }
    
    @Override
    public void previousPanel() {
        
        np.showNextButton();
        
        if (currentPanel>0) {
            showPreviousPanel();
            np.showPreviousButton();
        }
        
        if (currentPanel == 0)
            np.hidePreviousButton();
        
        
            
        
        
        
    }
    

    
}
