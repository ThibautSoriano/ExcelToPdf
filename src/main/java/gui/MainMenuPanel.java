package main.java.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import main.java.utils.Internationalization;
import main.java.utils.Language;
import main.java.utils.Message;

@SuppressWarnings("serial")
public class MainMenuPanel extends SettingsChoicePanel {

    private MainWindow mainWindow;

    public MainMenuPanel(MainWindow mainWindow) {
        super("Excel to pdf converter");

        this.mainWindow = mainWindow;

        title.setBounds(0, 20, PANEL_WIDTH, 30);
        setBounds(0, 0, 600, 490);

        JButton btnFromAnExcel = new JButton(
                "From an excel file on your computer");
        btnFromAnExcel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                mainWindow.createPanelsExcel();
                MainMenuPanel.this.mainWindow.setDownload(false);
            }
        });
        btnFromAnExcel.setBounds(44, 241, 224, 51);
        add(btnFromAnExcel);

        JButton btnByDownloadingDatas = new JButton("By downloading datas");
        btnByDownloadingDatas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainWindow.createPanelsInternet();
                MainMenuPanel.this.mainWindow.setDownload(true);
            }
        });

        
       
        btnByDownloadingDatas.setBounds(311, 241, 224, 51);
        add(btnByDownloadingDatas);

        JLabel lblChooseMode = new JLabel("Select how to import your files");
        lblChooseMode.setForeground(Color.BLACK);
        lblChooseMode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblChooseMode.setBounds(44, 130, 308, 67);
        add(lblChooseMode);

        JLabel lblBackground = new JLabel("");
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        lblBackground.setIcon(
                new ImageIcon(getClass().getResource("/background2.png")));
        lblBackground.setBounds(0, -23, 600, 490);
        add(lblBackground);

    }

    @Override
    public boolean isEveryThingOk(Message message) {

        return true;
    }

    @Override
    public SettingsChoicePanel getNewInstance() {

        return new MainMenuPanel(mainWindow);
    }

    private static void addMenu(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

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
            // rdbtnmntm.addMouseListener(new MouseAdapter() {
            // @Override
            // public void mouseReleased(MouseEvent e) {
            // Internationalization.loadLanguage(lang);
            //
            // getContentPane().remove(panels.get(currentPanel));
            // createPanels(currentPanel);
            //
            // getContentPane().remove(np);
            // np = np.getNewInstance();
            //
            // if (currentPanel == 0)
            // np.hidePreviousButton();
            // else if (currentPanel == panels.size() - 1)
            // np.showValidateButton();
            //
            // getContentPane().add(np);
            //
            // rdbtnmntm.setSelected(true);
            // repaint();
            // setVisible(true);
            // }
            // });

            // if (lang == Language.EN)
            // rdbtnmntm.setSelected(true);

            g.add(rdbtnmntm);
            mnLanguage.add(rdbtnmntm);
        }

    }

}
