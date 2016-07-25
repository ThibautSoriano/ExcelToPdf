package main.java.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import main.java.utils.Internationalization;

@SuppressWarnings("serial")
public class ColumnsSettingsPanel extends SettingsChoicePanel {

    private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    
    private boolean download;

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

    private List<JCheckBox> groupRankings;

    public static final int MAX_BOX_CHECKED = 5;

    private int cptRankingsChecked = 0;

    private int cptTechnicalChecked = 0;

    private List<JCheckBox> groupTechnical;

    private JCheckBox chckbxReach;

    private JCheckBox chckbxReachTechnical;
    
    

    public JCheckBox getChckbxReachTechnical() {
        return chckbxReachTechnical;
    }

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

    
    
    
    public JCheckBox getChckbxReach() {
        return chckbxReach;
    }

    public ColumnsSettingsPanel(boolean download) {
        super(Internationalization.getKey("Columns Settings"));

        
        
        this.download = download;
        
        groupRankings = new ArrayList<JCheckBox>();
        groupTechnical = new ArrayList<JCheckBox>();

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
            protected void paintFocusIndicator(Graphics g, int tabPlacement,
                    Rectangle[] rects, int tabIndex, Rectangle iconRect,
                    Rectangle textRect, boolean isSelected) {
                if (isSelected) {
                    g.setColor(new Color(104, 138, 200, 50));
                    g.fillRect(rects[tabIndex].x, rects[tabIndex].y,
                            rects[tabIndex].width, rects[tabIndex].height);

                }

                super.paintFocusIndicator(g, tabPlacement, rects, tabIndex,
                        iconRect, textRect, isSelected);
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
        
//        if (MainWindow.isRankings())
//            tabbedPane.addTab("Rankings", null, panelRankings, null);
        
        panelRankings.setLayout(null);

        chckbxImpressionsRankings = new JCheckBox("Impressions");
        chckbxImpressionsRankings.setBounds(93, 57, 150, 23);
        panelRankings.add(chckbxImpressionsRankings);
        groupRankings.add(chckbxImpressionsRankings);

        chckbxClicksRankings = new JCheckBox("Clicks");
        chckbxClicksRankings.setBounds(243, 57, 150, 23);
        panelRankings.add(chckbxClicksRankings);
        groupRankings.add(chckbxClicksRankings);

        chckbxUniqueCTRRankings = new JCheckBox("Unique CTR");
        chckbxUniqueCTRRankings.setBounds(393, 57, 150, 23);
        panelRankings.add(chckbxUniqueCTRRankings);
        groupRankings.add(chckbxUniqueCTRRankings);

        chckbxClickingUsersRankings = new JCheckBox("Clicking users");
        chckbxClickingUsersRankings.setBounds(243, 131, 150, 23);
        panelRankings.add(chckbxClickingUsersRankings);
        groupRankings.add(chckbxClickingUsersRankings);

        chckbxClickThroughRateRankings = new JCheckBox("Click through rate");
        chckbxClickThroughRateRankings.setBounds(243, 199, 150, 23);
        panelRankings.add(chckbxClickThroughRateRankings);
        groupRankings.add(chckbxClickThroughRateRankings);

        chckbxFrequencyRankings = new JCheckBox("Frequency");
        chckbxFrequencyRankings.setBounds(93, 199, 150, 23);
        panelRankings.add(chckbxFrequencyRankings);
        groupRankings.add(chckbxFrequencyRankings);

        JSeparator separator = new JSeparator();
        separator.setBounds(93, 33, 397, 2);
        panelRankings.add(separator);

        if (download) {
            
            chckbxReach = new JCheckBox("Reach");
            chckbxReach.setBounds(93, 131, 150, 23);
            panelRankings.add(chckbxReach);
            groupRankings.add(chckbxReach);
        }

        else {
            chckbxUniqueCookiesRankings = new JCheckBox("Unique cookies");
            chckbxUniqueCookiesRankings.setBounds(93, 131, 150, 23);
            panelRankings.add(chckbxUniqueCookiesRankings);
            groupRankings.add(chckbxUniqueCookiesRankings);

        }
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(93, 252, 397, 2);
        panelRankings.add(separator_1);

        
            JPanel panelTechnical = new JPanel();
            
//            if (MainWindow.isTechnical())
//                tabbedPane.addTab("Technical", null, panelTechnical, null);
            
            panelTechnical.setLayout(null);

            chckbxImpressionsTechnical = new JCheckBox("Impressions");
            chckbxImpressionsTechnical.setBounds(93, 57, 150, 23);
            panelTechnical.add(chckbxImpressionsTechnical);
            groupTechnical.add(chckbxImpressionsTechnical);

            chckbxClicksTechnical = new JCheckBox("Clicks");
            chckbxClicksTechnical.setBounds(243, 57, 150, 23);
            panelTechnical.add(chckbxClicksTechnical);
            groupTechnical.add(chckbxClicksTechnical);

            chckbxUniqueCTRTechnical = new JCheckBox("Unique CTR");
            chckbxUniqueCTRTechnical.setBounds(393, 57, 150, 23);
            panelTechnical.add(chckbxUniqueCTRTechnical);
            groupTechnical.add(chckbxUniqueCTRTechnical);

            chckbxClickingUsersTechnical = new JCheckBox("Clicking users");
            chckbxClickingUsersTechnical.setBounds(243, 131, 150, 23);
            panelTechnical.add(chckbxClickingUsersTechnical);
            groupTechnical.add(chckbxClickingUsersTechnical);

            chckbxClickThroughRateTechnical = new JCheckBox(
                    "Click through rate");
            chckbxClickThroughRateTechnical.setBounds(243, 199, 150, 23);
            panelTechnical.add(chckbxClickThroughRateTechnical);
            groupTechnical.add(chckbxClickThroughRateTechnical);

            chckbxFrequencyTechnical = new JCheckBox("Frequency");
            chckbxFrequencyTechnical.setBounds(93, 199, 150, 23);
            panelTechnical.add(chckbxFrequencyTechnical);
            groupTechnical.add(chckbxFrequencyTechnical);

            JSeparator separator_2 = new JSeparator();
            separator_2.setBounds(93, 33, 397, 2);
            panelTechnical.add(separator_2);

            if (download) {
                chckbxReachTechnical = new JCheckBox("Reach");
                chckbxReachTechnical.setBounds(93, 131, 150, 23);
                panelTechnical.add(chckbxReachTechnical);
                groupTechnical.add(chckbxReachTechnical);
            }
            else {
            chckbxUniqueCookiesTechnical = new JCheckBox("Unique cookies");
            chckbxUniqueCookiesTechnical.setBounds(93, 131, 150, 23);
            panelTechnical.add(chckbxUniqueCookiesTechnical);
            groupTechnical.add(chckbxUniqueCookiesTechnical);
            }
            JSeparator separator_3 = new JSeparator();
            separator_3.setBounds(93, 252, 397, 2);
            panelTechnical.add(separator_3);
        

        setListenerBoxClickable(groupRankings, true);
        setListenerBoxClickable(groupTechnical, false);

        addAncestorListener(new AncestorListener() {
            
            @Override
            public void ancestorRemoved(AncestorEvent event) {
                
            }
            
            @Override
            public void ancestorMoved(AncestorEvent event) {
                
            }
            
            @Override
            public void ancestorAdded(AncestorEvent event) {
                
                
                
                if (!MainWindow.isRankings() && !MainWindow.isTechnical())
                {
                    JLabel l = new JLabel("Nothing to choose for the selected modules");
                    l.setBounds(0,170,SettingsChoicePanel.PANEL_WIDTH,60);
                    l.setFont(new Font("Tahoma",Font.BOLD,16));
                    l.setHorizontalAlignment(SwingConstants.CENTER);
                    add(l);
                    return;
                }
                
                
                
                
                if (MainWindow.isRankings()) {
                    tabbedPane.addTab("Rankings", null, panelRankings, null);
                    tabbedPane.setSelectedIndex(0);
                    
                    
                }
                else {
                    
                    tabbedPane.remove(panelRankings);
                }
                
                if (MainWindow.isTechnical()) {
                    tabbedPane.addTab("Technical", null, panelTechnical, null);
                    
                    tabbedPane.setSelectedIndex(0);
                }
                else
                    tabbedPane.remove(panelTechnical);
                
                
            }
        });
        

       
    }

    @Override
    public boolean isEveryThingOk() {
        return true;
    }

    @Override
    public SettingsChoicePanel getNewInstance() {
        return new ColumnsSettingsPanel(download);
    }

    private void setListenerBoxClickable(List<JCheckBox> buttons,
            final boolean rankings) {
        for (final JCheckBox c : buttons) {
            c.addItemListener(new ItemListener() {

                @Override
                public void itemStateChanged(ItemEvent e) {

                    if (c.isSelected()) {
                        if (rankings)
                            cptRankingsChecked++;
                        else
                            cptTechnicalChecked++;

                    } else {
                        if (rankings)
                            cptRankingsChecked--;
                        else
                            cptTechnicalChecked--;
                    }
                    disableCheckBoxesIfNecessary();
                }

                private void disableCheckBoxesIfNecessary() {
                    if (cptRankingsChecked >= MAX_BOX_CHECKED) {
                        for (JCheckBox c : groupRankings) {
                            if (!c.isSelected())
                                c.setEnabled(false);
                        }
                    } else {
                        for (JCheckBox c : groupRankings) {
                            if (!c.isSelected())
                                c.setEnabled(true);
                        }
                    }

                    if (cptTechnicalChecked >= MAX_BOX_CHECKED) {
                        for (JCheckBox c : groupTechnical) {
                            if (!c.isSelected())
                                c.setEnabled(false);
                        }
                    } else {
                        for (JCheckBox c : groupTechnical) {
                            if (!c.isSelected())
                                c.setEnabled(true);
                        }
                    }

                }
            });
        }
    }
}
