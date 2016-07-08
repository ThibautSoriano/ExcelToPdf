package main.java.gui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;

import main.java.utils.Message;

public class ColumnsSettingsPanel extends SettingsChoicePanel {

    /**
     * 
     */
    private static final long serialVersionUID = -6731736790059514831L;

    private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

    private JCheckBox chckbxImpressionsRankings;

    private JCheckBox chckbxClicksRankings;

    private JCheckBox chckbxUniqueCTRRankings;

    private JCheckBox chckbxClickingUsersRankings;

    private JCheckBox chckbxClickThroughRateRankings;

    private JCheckBox chckbxFrequencyRankings;

    private JCheckBox chckbxUniqueCookiesRankings;

    private JCheckBox chckbxImpressionsTechnical;

    private JCheckBox chckbxClicksTechnical;

    private JCheckBox chckbxUniqueCTRTechnical;

    private JCheckBox chckbxClickingUsersTechnical;

    private JCheckBox chckbxClickThroughRateTechnical;

    private JCheckBox chckbxFrequencyTechnical;

    private JCheckBox chckbxUniqueCookiesTechnical;

    
    
    
    
    
    public JCheckBox getChckbxImpressionsRankings() {
        return chckbxImpressionsRankings;
    }

    public JCheckBox getChckbxClicksRankings() {
        return chckbxClicksRankings;
    }

    public JCheckBox getChckbxUniqueCTRRankings() {
        return chckbxUniqueCTRRankings;
    }

    public JCheckBox getChckbxClickingUsersRankings() {
        return chckbxClickingUsersRankings;
    }

    public JCheckBox getChckbxClickThroughRateRankings() {
        return chckbxClickThroughRateRankings;
    }

    public JCheckBox getChckbxFrequencyRankings() {
        return chckbxFrequencyRankings;
    }

    public JCheckBox getChckbxUniqueCookiesRankings() {
        return chckbxUniqueCookiesRankings;
    }

    public JCheckBox getChckbxImpressionsTechnical() {
        return chckbxImpressionsTechnical;
    }

    public JCheckBox getChckbxClicksTechnical() {
        return chckbxClicksTechnical;
    }

    public JCheckBox getChckbxUniqueCTRTechnical() {
        return chckbxUniqueCTRTechnical;
    }

    public JCheckBox getChckbxClickingUsersTechnical() {
        return chckbxClickingUsersTechnical;
    }

    public JCheckBox getChckbxClickThroughRateTechnical() {
        return chckbxClickThroughRateTechnical;
    }

    public JCheckBox getChckbxFrequencyTechnical() {
        return chckbxFrequencyTechnical;
    }

    public JCheckBox getChckbxUniqueCookiesTechnical() {
        return chckbxUniqueCookiesTechnical;
    }

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

        chckbxImpressionsRankings = new JCheckBox("Impressions");
        chckbxImpressionsRankings.setBounds(93, 57, 97, 23);
        panelRankings.add(chckbxImpressionsRankings);

         chckbxClicksRankings = new JCheckBox("Clicks");
        chckbxClicksRankings.setBounds(243, 57, 97, 23);
        panelRankings.add(chckbxClicksRankings);

        chckbxUniqueCTRRankings = new JCheckBox("Unique CTR");
        chckbxUniqueCTRRankings.setBounds(393, 57, 97, 23);
        panelRankings.add(chckbxUniqueCTRRankings);

         chckbxClickingUsersRankings = new JCheckBox("Clicking users");
        chckbxClickingUsersRankings.setBounds(243, 131, 97, 23);
        panelRankings.add(chckbxClickingUsersRankings);

         chckbxClickThroughRateRankings = new JCheckBox(
                "Click through rate");
        chckbxClickThroughRateRankings.setBounds(243, 199, 120, 23);
        panelRankings.add(chckbxClickThroughRateRankings);

         chckbxFrequencyRankings = new JCheckBox("Frequency");
        chckbxFrequencyRankings.setBounds(93, 199, 97, 23);
        panelRankings.add(chckbxFrequencyRankings);

        JSeparator separator = new JSeparator();
        separator.setBounds(93, 33, 397, 2);
        panelRankings.add(separator);

         chckbxUniqueCookiesRankings = new JCheckBox("Unique cookies");
        chckbxUniqueCookiesRankings.setBounds(93, 131, 97, 23);
        panelRankings.add(chckbxUniqueCookiesRankings);

         JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(93, 252, 397, 2);
        panelRankings.add(separator_1);

        JPanel panelTechnical = new JPanel();
        tabbedPane.addTab("Technical", null, panelTechnical, null);
        panelTechnical.setLayout(null);

         chckbxImpressionsTechnical = new JCheckBox("Impressions");
        chckbxImpressionsTechnical.setBounds(93, 57, 97, 23);
        panelTechnical.add(chckbxImpressionsTechnical);

         chckbxClicksTechnical = new JCheckBox("Clicks");
        chckbxClicksTechnical.setBounds(243, 57, 97, 23);
        panelTechnical.add(chckbxClicksTechnical);

         chckbxUniqueCTRTechnical = new JCheckBox("Unique CTR");
        chckbxUniqueCTRTechnical.setBounds(393, 57, 97, 23);
        panelTechnical.add(chckbxUniqueCTRTechnical);

         chckbxClickingUsersTechnical = new JCheckBox(
                "Clicking users");
        chckbxClickingUsersTechnical.setBounds(243, 131, 97, 23);
        panelTechnical.add(chckbxClickingUsersTechnical);

         chckbxClickThroughRateTechnical = new JCheckBox(
                "Click through rate");
        chckbxClickThroughRateTechnical.setBounds(243, 199, 120, 23);
        panelTechnical.add(chckbxClickThroughRateTechnical);

         chckbxFrequencyTechnical = new JCheckBox("Frequency");
        chckbxFrequencyTechnical.setBounds(93, 199, 97, 23);
        panelTechnical.add(chckbxFrequencyTechnical);

        JSeparator separator_2 = new JSeparator();
        separator_2.setBounds(93, 33, 397, 2);
        panelTechnical.add(separator_2);

         chckbxUniqueCookiesTechnical = new JCheckBox(
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
