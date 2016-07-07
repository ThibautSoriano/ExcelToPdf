package main.java.gui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import main.java.utils.Message;

public class ColumnsSettingsPanel extends SettingsChoicePanel {

    /**
     * 
     */
    private static final long serialVersionUID = -6731736790059514831L;

    private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

    public ColumnsSettingsPanel() {
        super("Columns Settings");
        
        tabbedPane.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
            @Override
            protected int calculateTabHeight(int tabPlacement, int tabIndex,
                    int fontHeight) {
                return 32;
            }

            @Override
            protected int calculateTabWidth(int tabPlacement, int tabIndex,
                    FontMetrics metrics) {
                return SettingsChoicePanel.PANEL_WIDTH / 2 - 3;
            }

            @Override
            protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects,
                    int tabIndex, Rectangle iconRect, Rectangle textRect,
                    boolean isSelected) {
                if (isSelected) {
                    g.setColor(new Color(104,138,200,50));
                    g.fillRect(rects[tabIndex].x, rects[tabIndex].y, rects[tabIndex].width, rects[tabIndex].height);
                    System.out.println(g.getColor());
                }
                
                super.paintFocusIndicator(g, tabPlacement, rects, tabIndex, iconRect, textRect, isSelected);
            }

            @Override
            protected void paintTab(Graphics g, int tabPlacement,
                    Rectangle[] rects, int tabIndex, Rectangle iconRect,
                    Rectangle textRect) {

                super.paintTab(g, tabPlacement, rects, tabIndex, iconRect,
                        textRect);
            }
        });

        tabbedPane.setBounds(-2, 66, 799, 514);
        add(tabbedPane);

        JPanel panelRankings = new JPanel();
        tabbedPane.addTab("Rankings", null, panelRankings, null);
        panelRankings.setLayout(null);

        JCheckBox chckbxImpressionsRankings = new JCheckBox("Impressions");
        chckbxImpressionsRankings.setBounds(93, 57, 97, 23);
        panelRankings.add(chckbxImpressionsRankings);

        JCheckBox chckbxClicksRankings = new JCheckBox("Clicks");
        chckbxClicksRankings.setBounds(243, 57, 97, 23);
        panelRankings.add(chckbxClicksRankings);

        JCheckBox chckbxUniqueCTRRankings = new JCheckBox("Unique CTR");
        chckbxUniqueCTRRankings.setBounds(393, 57, 97, 23);
        panelRankings.add(chckbxUniqueCTRRankings);

        JCheckBox chckbxClickingUsersRankings = new JCheckBox("Clicking users");
        chckbxClickingUsersRankings.setBounds(243, 131, 97, 23);
        panelRankings.add(chckbxClickingUsersRankings);

        JCheckBox chckbxClickThroughRateRankings = new JCheckBox(
                "Click through rate");
        chckbxClickThroughRateRankings.setBounds(243, 199, 120, 23);
        panelRankings.add(chckbxClickThroughRateRankings);

        JCheckBox chckbxFrequencyRankings = new JCheckBox("Frequency");
        chckbxFrequencyRankings.setBounds(93, 199, 97, 23);
        panelRankings.add(chckbxFrequencyRankings);

        JSeparator separator = new JSeparator();
        separator.setBounds(93, 33, 397, 2);
        panelRankings.add(separator);

        JCheckBox chckbxUniqueCookiesRankings = new JCheckBox("Unique cookies");
        chckbxUniqueCookiesRankings.setBounds(93, 131, 97, 23);
        panelRankings.add(chckbxUniqueCookiesRankings);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(93, 252, 397, 2);
        panelRankings.add(separator_1);

        JPanel panelTechnical = new JPanel();
        tabbedPane.addTab("Technical", null, panelTechnical, null);
        panelTechnical.setLayout(null);

        JCheckBox chckbxImpressionsTechnical = new JCheckBox("Impressions");
        chckbxImpressionsTechnical.setBounds(93, 57, 97, 23);
        panelTechnical.add(chckbxImpressionsTechnical);

        JCheckBox chckbxClicksTechnical = new JCheckBox("Clicks");
        chckbxClicksTechnical.setBounds(243, 57, 97, 23);
        panelTechnical.add(chckbxClicksTechnical);

        JCheckBox chckbxUniqueCTRTechnical = new JCheckBox("Unique CTR");
        chckbxUniqueCTRTechnical.setBounds(393, 57, 97, 23);
        panelTechnical.add(chckbxUniqueCTRTechnical);

        JCheckBox chckbxClickingUsersTechnical = new JCheckBox(
                "Clicking users");
        chckbxClickingUsersTechnical.setBounds(243, 131, 97, 23);
        panelTechnical.add(chckbxClickingUsersTechnical);

        JCheckBox chckbxClickThroughRateTechnical = new JCheckBox(
                "Click through rate");
        chckbxClickThroughRateTechnical.setBounds(243, 199, 120, 23);
        panelTechnical.add(chckbxClickThroughRateTechnical);

        JCheckBox chckbxFrequencyTechnical = new JCheckBox("Frequency");
        chckbxFrequencyTechnical.setBounds(93, 199, 97, 23);
        panelTechnical.add(chckbxFrequencyTechnical);

        JSeparator separator_2 = new JSeparator();
        separator_2.setBounds(93, 33, 397, 2);
        panelTechnical.add(separator_2);

        JCheckBox chckbxUniqueCookiesTechnical = new JCheckBox(
                "Unique cookies");
        chckbxUniqueCookiesTechnical.setBounds(93, 131, 97, 23);
        panelTechnical.add(chckbxUniqueCookiesTechnical);

        JSeparator separator_3 = new JSeparator();
        separator_3.setBounds(93, 252, 397, 2);
        panelTechnical.add(separator_3);
    }

    @Override
    public boolean isEveryThingOk(Message message) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public SettingsChoicePanel getNewInstance() {
        // TODO Auto-generated method stub
        return null;
    }
}
