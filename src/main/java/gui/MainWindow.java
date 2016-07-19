package main.java.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
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

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements IMainFrame {

    private List<SettingsChoicePanel> panels;
    private int currentPanel = 0;
    private NavigationPanel np;
    private boolean download;

    private static final int WINDOW_HEIGHT = 500;

    private static final int WINDOW_WIDTH = 600;

    private static HttpDownload session;
    
    
    
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

        if (download) {
            validationDownload();
        } else {
            ExcelToPdf etpd = new ExcelToPdf();
            List<Section> sections = new ArrayList<Section>();

            ExcelChoicePanel mwp = (ExcelChoicePanel) panels.get(0);
            GeneralSettingsPanel gsp = (GeneralSettingsPanel) panels.get(1);
            TitleSettingsPanel tsp = (TitleSettingsPanel) panels.get(2);
            InsertPageSettingsPanel isp = (InsertPageSettingsPanel) panels
                    .get(3);
            ColumnsSettingsPanel csp = (ColumnsSettingsPanel) panels.get(4);

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

            ip.setCustomTextArea(isp.getTxtrCreatedBy().getText());
            ip.setStructure(hfInsert);

            sections.add(ip);

            // Columns choice settings
            List<String> excelPaths = new ArrayList<String>();
            List<JTextField> fields = mwp.getFields();

            for (JTextField jTextField : fields) {
                String src = jTextField.getText();
                if (!src.isEmpty()) {
                    ContentPage contentPage = null;
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

                    excelPaths.add(src);

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
                    contentPage.setExcelReader(excelReader);
                    sections.add(contentPage);
                }
            }

            try {
                etpd.createPdf(excelPaths, "orbegozo.pdf", sections,
                        isp.getRdbtnOn().isSelected());
            } catch (IOException | DocumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    private void validationDownload() {
        ExcelToPdf etpd = new ExcelToPdf();
        List<Section> sections = new ArrayList<Section>();

        CampaignChoicePanel ccp = (CampaignChoicePanel) panels.get(1);
        GeneralSettingsPanel gsp = (GeneralSettingsPanel) panels.get(2);
        TitleSettingsPanel tsp = (TitleSettingsPanel) panels.get(3);
        InsertPageSettingsPanel isp = (InsertPageSettingsPanel) panels.get(4);
        ColumnsSettingsPanel csp = (ColumnsSettingsPanel) panels.get(5);

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

        ip.setCustomTextArea(isp.getTxtrCreatedBy().getText());
        ip.setStructure(hfInsert);

        sections.add(ip);

        // Columns choice settings

        ContentPage contentPage = new ContentPage(
                csp.getChckbxImpressionsRankings().isSelected(),
                csp.getChckbxFrequencyRankings().isSelected(),
                csp.getChckbxClicksRankings().isSelected(),
                csp.getChckbxClickingUsersRankings().isSelected(),
                csp.getChckbxClickThroughRateRankings().isSelected(),
                csp.getChckbxUniqueCTRRankings().isSelected());

        contentPage.setReach(csp.getChckbxReach().isSelected());

        HeaderFooter hfContent = new HeaderFooter(
                gsp.getChckbxHeader().isSelected(),
                gsp.getChckbxHeaderLine().isSelected(),
                gsp.getChckbxFooter().isSelected(),
                gsp.getChckbxFooterLine().isSelected(), positionPageCount);
        hfContent.setLineInHeader(gsp.getTxtWebsite().getText());
        hfContent.setLineInFooter(gsp.getTxtBottomLeftText().getText());
        hfContent.setLogoInHeader(gsp.getTxtLogo().getText());
        contentPage.setStructure(hfContent);
        
        
        sections.add(contentPage);
        
    
    String campaignID = ccp.getSelectedId();
   
    
    Campaign c = session.getCampaignById(campaignID);
    
    if (c == null) {
        JOptionPane.showMessageDialog(null,"The connection with the server failed","ERROR",JOptionPane.ERROR_MESSAGE);
        return;
    }
    
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
    c.setColumsLabels(labels);
    try{
        etpd.createPdfDownload(c,"orbegozo_online.pdf", sections, isp.getRdbtnOn().isSelected());
    }
    catch (DocumentException | IOException e)
    {
        // TODO Auto-generated catch block
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

        CampaignChoicePanel ccp = new CampaignChoicePanel();
        
        panels = new LinkedList<SettingsChoicePanel>();

        panels.add(new LoginPanel(ccp));
        panels.add(ccp);
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
