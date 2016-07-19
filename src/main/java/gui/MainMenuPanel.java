package main.java.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

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

        lblBackground.setIcon(
                new ImageIcon(getClass().getResource("/background2.png")));
        lblBackground.setBounds(0, -23, 600, 490);
        add(lblBackground);

    }

    @Override
    public boolean isEveryThingOk() {
        return true;
    }

    @Override
    public SettingsChoicePanel getNewInstance() {

        return new MainMenuPanel(mainWindow);
    }


}
