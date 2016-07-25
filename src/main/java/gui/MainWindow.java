package main.java.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.text.DocumentException;

import main.java.datasdownloading.HttpDownload;
import main.java.datasdownloading.entities.Campaign;
import main.java.datasdownloading.entities.SummaryData;
import main.java.excelreader.ExcelReader;
import main.java.excelreader.ExcelReaderRankings;
import main.java.excelreader.ExcelReaderTechnical;
import main.java.excelreader.entities.CampaignRow;
import main.java.exceltopdf.ExcelToPdf;
import main.java.exceltopdf.HeaderFooter;
import main.java.exceltopdf.pdfsections.ContentPage;
import main.java.exceltopdf.pdfsections.InsertPage;
import main.java.exceltopdf.pdfsections.Section;
import main.java.exceltopdf.pdfsections.SummaryPage;
import main.java.exceltopdf.pdfsections.TitlePage;
import main.java.utils.FileType;
import main.java.utils.Internationalization;
import main.java.utils.InternationalizationPDF;
import main.java.utils.Language;
import main.java.utils.Utils;

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements IMainFrame {

    private List<SettingsChoicePanel> panels;
    private int currentPanel = 0;
    private NavigationPanel np;
    private boolean download;

    private static final int WINDOW_HEIGHT = 500;

    private static final int WINDOW_WIDTH = 600;

    private static HttpDownload session;
    
    
    private static boolean isRankings;
    
    private static boolean isTechnical;
    
    private static boolean isSummary;
    
 
    public static boolean isRankings() {
        return isRankings;
    }
    
    

    public static boolean isTechnical() {
        return isTechnical;
    }



    public static void setRankings(boolean isRankings) {
        MainWindow.isRankings = isRankings;
    }

    public static void setTechnical(boolean isTechnical) {
        MainWindow.isTechnical = isTechnical;
    }
    
    public static void setSummary(boolean isSummary) {
    	MainWindow.isSummary = isSummary;
    }
    
    public static HttpDownload getSession() {
        return session;
    }

    public static void setSession(HttpDownload session) {
        MainWindow.session = session;
    }

    public boolean isDownload() {
        return download;
    }

    public void setDownload(boolean download) {
        this.download = download;
    }

    public MainWindow() {
        
        
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                
                    if (session != null)
                        session.close();
            }
        });
        
        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit()
                .getImage(getClass().getResource("/icon.png")));
        setBounds(200, 100, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle(Internationalization.getKey("Converter"));

        getContentPane().setLayout(null);

        showFirstPanel();

        //

        addMenu();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        
        
        
//        JLabel lblBackground = new JLabel("");
//        lblBackground.setIcon(
//              new ImageIcon(getClass().getResource("/blue.png")));
//        
//        lblBackground.setBounds(0, -23, 600, 490);
//        
//        add(lblBackground);
        
        
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
        mntmExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {
                if (session != null)
                    session.close();
                System.exit(0);
            }
        });
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

                    reloadPanels();

                    getContentPane().remove(np);
                    np = np.getNewInstance();

                    if (currentPanel == 0)
                        np.hidePreviousButton();
                    else if (currentPanel == panels.size() - 1)
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
        
        
        Language pdfLang = Language.EN;
        
        //FIXME TODO ZHENGQIN  creer une InternationalizationPDF + combobox pour choix langage pdf + utiliser l'objet creer pour
        //remplir champs
        

        if (download) {
            validationDownload();
        } else {
            
            
            List<Section> sections = new ArrayList<Section>();

            ExcelChoicePanel ecp = (ExcelChoicePanel) panels.get(0);
            GeneralSettingsPanel gsp = (GeneralSettingsPanel) panels.get(1);
            TitleSettingsPanel tsp = (TitleSettingsPanel) panels.get(2);
            InsertPageSettingsPanel isp = (InsertPageSettingsPanel) panels
                    .get(3);
            ColumnsSettingsPanel csp = (ColumnsSettingsPanel) panels.get(4);
            
            
            InternationalizationPDF ipdf = new InternationalizationPDF(ecp.getSelectedLanguage());
            ExcelToPdf etpd = new ExcelToPdf(ecp.getSelectedLanguage().getEncoding());

            int positionPageCount = gsp.getRdbtnBottomCenter().isSelected()
                    ? HeaderFooter.PAGE_COUNT_MIDDLE
                    : HeaderFooter.PAGE_COUNT_RIGHT;

            // Infos for the title page
            TitlePage tp = new TitlePage();

            HeaderFooter hfTitle = new HeaderFooter(
                    tsp.getChckbxHeader().isSelected(),
                    tsp.getChckbxSeparatorLineBelowHeader().isSelected(),
                    tsp.getChckbxFooter().isSelected(),
                    tsp.getChckbxSeparatorLineAboveFooter().isSelected(),
                    HeaderFooter.NO_PAGE_COUNT);

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
                    isp.getChckbxSeparatorLineAbove().isSelected(),
                    HeaderFooter.NO_PAGE_COUNT);

            hfInsert.setLineInHeader(gsp.getTxtWebsite().getText());
            hfInsert.setLineInFooter(gsp.getTxtBottomLeftText().getText());
            hfInsert.setLogoInHeader(gsp.getTxtLogo().getText());

            ip.setCustomTextArea(Utils.splitLongTextToFitPage(isp.getTxtrCreatedBy().getText()));
            ip.setStructure(hfInsert);

            sections.add(ip);

            // Columns choice settings
            
            List<JTextField> fields = ecp.getFields();
            ContentPage contentPage = null;
            for (JTextField jTextField : fields) {
                String src = jTextField.getText();
                if (!src.isEmpty()) {
                    
                    ExcelReader excelReader = null;

                    if (src.contains("Rankings")) {
                        excelReader = new ExcelReaderRankings();
                        contentPage = new ContentPage(
                                csp.getChckbxImpressionsRankings().isSelected(),
                                csp.getChckbxFrequencyRankings().isSelected(),
                                csp.getChckbxClicksRankings().isSelected(),
                                csp.getChckbxClickingUsersRankings()
                                        .isSelected(),
                                csp.getChckbxClickThroughRateRankings()
                                        .isSelected(),
                                csp.getChckbxUniqueCTRRankings().isSelected());
                        contentPage.setUniqueCookies(csp
                                .getChckbxUniqueCookiesRankings().isSelected());
   
                    } else if (src.contains("Technical")) {
                        excelReader = new ExcelReaderTechnical();
                        contentPage = new ContentPage(
                                csp.getChckbxImpressionsTechnical()
                                        .isSelected(),
                                csp.getChckbxFrequencyTechnical().isSelected(),
                                csp.getChckbxClicksTechnical().isSelected(),
                                csp.getChckbxClickingUsersTechnical()
                                        .isSelected(),
                                csp.getChckbxClickThroughRateTechnical()
                                        .isSelected(),
                                csp.getChckbxUniqueCTRTechnical().isSelected());
                        contentPage.setUniqueCookies(
                                csp.getChckbxUniqueCookiesTechnical()
                                        .isSelected());
                    } else {
                        System.err.println("xls not recognized");
                        return;
                    }
                    SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                    Campaign zk = excelReader.readExcelSheet(src);
                    contentPage.setCampaign(zk);
                    
                    tp.setCampaignName(zk.getCampaignHeader().getCampaignName());
                    tp.setDate(ipdf.getDateFromTo(zk.getCampaignHeader().getStartDate(), zk.getCampaignHeader().getEndDate()));
                    

                    HeaderFooter hfContent = new HeaderFooter(
                            gsp.getChckbxHeader().isSelected(),
                            gsp.getChckbxHeaderLine().isSelected(),
                            gsp.getChckbxFooter().isSelected(),
                            gsp.getChckbxFooterLine().isSelected(),
                            positionPageCount);
                    hfContent.setLineInHeader(gsp.getTxtWebsite().getText());
                    hfContent.setLineInFooter(
                            gsp.getTxtBottomLeftText().getText());
                    hfContent.setLogoInHeader(gsp.getTxtLogo().getText());
                    contentPage.setStructure(hfContent);
                    sections.add(contentPage);
                }
            }

            
           
            
            
            try {
                etpd.createPdf(Utils.getPdfName(contentPage.getCampaign().getCampaignHeader().getCampaignName()), sections,
                        isp.getRdbtnOn().isSelected());
            } catch (IOException | DocumentException e) {
                e.printStackTrace();
            }
        }

    }

    private void validationDownload() {
        
     
        List<SummaryData> summary = new ArrayList<>();
        
        
        List<Section> sections = new ArrayList<Section>();

        CampaignChoicePanel ccp = (CampaignChoicePanel) panels.get(1);
        ModulesSettingsPanel msp = (ModulesSettingsPanel) panels.get(2);
        GeneralSettingsPanel gsp = (GeneralSettingsPanel) panels.get(3);
        TitleSettingsPanel tsp = (TitleSettingsPanel) panels.get(4);
        InsertPageSettingsPanel isp = (InsertPageSettingsPanel) panels.get(5);
        ColumnsSettingsPanel csp = (ColumnsSettingsPanel) panels.get(6);

        
        InternationalizationPDF ipdf = new InternationalizationPDF(msp.getSelectedLanguage());
        ExcelToPdf etpd = new ExcelToPdf(msp.getSelectedLanguage().getEncoding());
        
        
        String campaignID = ccp.getSelectedId();
        
        Campaign c1 = null,c2 = null;
        boolean error = false;
        
         c1 = session.getCampaignRankingsById(campaignID);
         if (c1 == null)
             error = true;
        
        if (msp.getChckbxTechnical().isSelected()) {
            c2 = session.getCampaignTechnicalById(campaignID);
            if (c2 == null)
                error = true;
        }
        
        if (error) {
            JOptionPane.showMessageDialog(null,"The connection with the server failed","ERROR",JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Campaign commonInfos  = (c1 == null) ? c2 : c1;
        
        int positionPageCount = gsp.getRdbtnBottomCenter().isSelected()
                ? HeaderFooter.PAGE_COUNT_MIDDLE
                : HeaderFooter.PAGE_COUNT_RIGHT;
        
        

        // Infos for the title page
        TitlePage tp = new TitlePage();

        HeaderFooter hfTitle = new HeaderFooter(
                tsp.getChckbxHeader().isSelected(),
                tsp.getChckbxSeparatorLineBelowHeader().isSelected(),
                tsp.getChckbxFooter().isSelected(),
                tsp.getChckbxSeparatorLineAboveFooter().isSelected(),
                HeaderFooter.NO_PAGE_COUNT);

        hfTitle.setLineInFooter(gsp.getTxtBottomLeftText().getText());
        hfTitle.setLineInHeader(gsp.getTxtWebsite().getText());
        hfTitle.setLogoInHeader(gsp.getTxtLogo().getText());
        
        tp.setStructure(hfTitle);
        tp.setBelowTitle(Utils.splitLongTextToFitPage(tsp.getTxtrBelowTitle().getText()));
        System.out.println(Utils.splitLongTextToFitPage(tsp.getTxtrBelowTitle().getText()));
        tp.setCampaignName(commonInfos.getCampaignHeader().getCampaignName());
        
        
        
        
        tp.setDate(ipdf.getDateFromTo(commonInfos.getCampaignHeader().getStartDate(), commonInfos.getCampaignHeader().getEndDate()));
       
        sections.add(tp);

        // Infos for the insert page
        InsertPage ip = new InsertPage();

        HeaderFooter hfInsert = new HeaderFooter(
                isp.getChckbxHeader().isSelected(),
                isp.getChckbxSeparatorLineBelow().isSelected(),
                isp.getChckbxFooter().isSelected(),
                isp.getChckbxSeparatorLineAbove().isSelected(),
                HeaderFooter.NO_PAGE_COUNT);

        hfInsert.setLineInHeader(gsp.getTxtWebsite().getText());
        hfInsert.setLineInFooter(gsp.getTxtBottomLeftText().getText());
        hfInsert.setLogoInHeader(gsp.getTxtLogo().getText());

        ip.setCustomTextArea(isp.getTxtrCreatedBy().getText());
        ip.setStructure(hfInsert);

        sections.add(ip);

        
        //it is the same for the two possible content pages
        HeaderFooter hfContent = new HeaderFooter(
                gsp.getChckbxHeader().isSelected(),
                gsp.getChckbxHeaderLine().isSelected(),
                gsp.getChckbxFooter().isSelected(),
                gsp.getChckbxFooterLine().isSelected(), positionPageCount);
        hfContent.setLineInHeader(gsp.getTxtWebsite().getText());
        hfContent.setLineInFooter(gsp.getTxtBottomLeftText().getText());
        hfContent.setLogoInHeader(gsp.getTxtLogo().getText());
        
        
        // Columns choice settings
        
        
        List<String> labels = new ArrayList<>();
        labels.add("Placement path");
        labels.add("Impressions");
        labels.add("Unique cookies");
        labels.add("Frequency");
        labels.add("Clicks");
        labels.add("Clicking users");
        labels.add("Click Through Rate");
        labels.add("Unique CTR");
        labels.add("Reach");
        
        if (msp.getChckbxSummary().isSelected()) {
                DecimalFormat df = new DecimalFormat("#.##");
                df.setRoundingMode(RoundingMode.CEILING);
        	SimpleDateFormat hungarianF = new SimpleDateFormat("yyyy. MM. dd.");
        	
        	summary.add(new SummaryData("campaign name:", c1.getCampaignHeader().getCampaignName()));
        	summary.add(new SummaryData("client name:", c1.getCampaignHeader().getClientName()));
        	summary.add(new SummaryData("campaign start date:", hungarianF.format(c1.getCampaignHeader().getStartDate())));
        	summary.add(new SummaryData("campaign end date:", hungarianF.format(c1.getCampaignHeader().getEndDate())));
        	summary.add(new SummaryData("impressions:", CampaignRow.getSpacesBetweenThousands(String.valueOf(c1.getAll().getImpressions()))));
        	summary.add(new SummaryData("reach:", CampaignRow.getSpacesBetweenThousands(String.valueOf(c1.getAll().getReach()))));
        	summary.add(new SummaryData("frequency:", df.format(c1.getAll().getFrequency())));
        	summary.add(new SummaryData("clicks:", CampaignRow.getSpacesBetweenThousands(String.valueOf(c1.getAll().getClicks()))));
        	summary.add(new SummaryData("clicking users:", CampaignRow.getSpacesBetweenThousands(String.valueOf(c1.getAll().getClickingUsers()))));
        	summary.add(new SummaryData("click through rate:", c1.getAll().getClickThroughRate().toString()));
        	summary.add(new SummaryData("unique CTR:", c1.getAll().getUniqueCTR().toString()));
        }
        
        SummaryPage summaryPage = new SummaryPage(summary, msp.getChckbxSummary().isSelected());
        
        summaryPage.setStructure(hfContent);
        sections.add(summaryPage);
        
        if (msp.getChckbxRankings().isSelected()) {
            ContentPage contentPage = new ContentPage(
                    csp.getChckbxImpressionsRankings().isSelected(),
                    csp.getChckbxFrequencyRankings().isSelected(),
                    csp.getChckbxClicksRankings().isSelected(),
                    csp.getChckbxClickingUsersRankings().isSelected(),
                    csp.getChckbxClickThroughRateRankings().isSelected(),
                    csp.getChckbxUniqueCTRRankings().isSelected());
    
            contentPage.setReach(csp.getChckbxReach().isSelected());
            c1.setTechnicalCampaign(false);
            contentPage.setStructure(hfContent);
            c1.setColumsLabels(labels);
            contentPage.setCampaign(c1);
            sections.add(contentPage);
        }
        
        if (msp.getChckbxTechnical().isSelected()) {
            ContentPage contentPage2 = new ContentPage(
                    csp.getChckbxImpressionsTechnical().isSelected(),
                    csp.getChckbxFrequencyTechnical().isSelected(),
                    csp.getChckbxClicksTechnical().isSelected(),
                    csp.getChckbxClickingUsersTechnical().isSelected(),
                    csp.getChckbxClickThroughRateTechnical().isSelected(),
                    csp.getChckbxUniqueCTRTechnical().isSelected());
            
            contentPage2.setReach(csp.getChckbxReachTechnical().isSelected());
            c2.setTechnicalCampaign(true);
            contentPage2.setStructure(hfContent);
            c2.setColumsLabels(labels);
            contentPage2.setCampaign(c2);
            sections.add(contentPage2);
            
        }
        
         try{
         etpd.createPdfDownload(Utils.getPdfName(commonInfos.getCampaignHeader().getCampaignName()), sections,
         isp.getRdbtnOn().isSelected());
         }
         catch (DocumentException | IOException e)
         {
         e.printStackTrace();
         }


    }

    private void reloadPanels() {
        LinkedList<SettingsChoicePanel> panels2 = new LinkedList<SettingsChoicePanel>();
        for (SettingsChoicePanel settingsChoicePanel : panels) {
            panels2.add(settingsChoicePanel.getNewInstance());
        }
        getContentPane().remove(panels.get(currentPanel));
        panels = panels2;
        getContentPane().add(panels.get(currentPanel));
    }

    public void createPanelsExcel() {

        getContentPane().remove(panels.get(currentPanel));

        panels = new LinkedList<SettingsChoicePanel>();
        panels.add(new ExcelChoicePanel());
        panels.add(new GeneralSettingsPanel());
        panels.add(new TitleSettingsPanel());
        panels.add(new InsertPageSettingsPanel());
        panels.add(new ColumnsSettingsPanel(false));

        currentPanel = 0;

        np = new NavigationPanel(this);

        getContentPane().add(np);

        getContentPane().add(panels.get(0));
        repaint();
        revalidate();

    }

    public void createPanelsInternet() {

        getContentPane().remove(panels.get(currentPanel));

        
        
        panels = new LinkedList<SettingsChoicePanel>();

        panels.add(new LoginPanel());
        panels.add(new CampaignChoicePanel());
        panels.add(new ModulesSettingsPanel());
        panels.add(new GeneralSettingsPanel());
        panels.add(new TitleSettingsPanel());
        panels.add(new InsertPageSettingsPanel());
        panels.add(new ColumnsSettingsPanel(true));

        currentPanel = 0;

        np = new NavigationPanel(this);

        getContentPane().add(np);

        getContentPane().add(panels.get(0));
        repaint();
        revalidate();

    }

    @Override
    public void nextPanel() {
        if (panels.get(currentPanel).isEveryThingOk()) {

            np.showPreviousButton();
            showNextPanel();
            if (currentPanel == panels.size() - 1) {
                np.showValidateButton();

            }

        }
        // else
        // System.out.println(m.getMessages());

        
        
        
    }

    @Override
    public void previousPanel() {
        np.showNextButton();

        if (currentPanel > 0) {
            showPreviousPanel();
            np.showPreviousButton();
        }

        else if (currentPanel == 0) {
            getContentPane().remove(panels.get(currentPanel));
            getContentPane().remove(np);
            showFirstPanel();
            repaint();
            revalidate();
        }
        
        

    }

    private void showFirstPanel() {
        panels = new LinkedList<SettingsChoicePanel>();
        MainMenuPanel mmp = new MainMenuPanel(this);
        panels.add(mmp);

        getContentPane().add(panels.get(0));

        currentPanel = 0;
    }
    
    

}
