package main.java.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.EmptyBorder;

import main.java.utils.FileType;
import main.java.utils.Internationalization;
import main.java.utils.Message;

public class TitleSettingsPanel extends SettingsChoicePanel{

    
    
    private JTextField txtLogo;
    private JTextField txtBottomLeftText;
    private JCheckBox checkBox;
    private JCheckBox checkBox_1;
    private JCheckBox checkBox_2;
    private JCheckBox checkBox_3;
    private JTextArea txtrBelowTitle;
    
    
    public JTextField getTxtLogo() {
        return txtLogo;
    }

    public JTextField getTxtBottomLeftText() {
        return txtBottomLeftText;
    }

    public JCheckBox getCheckBox() {
        return checkBox;
    }

    public JCheckBox getCheckBox_1() {
        return checkBox_1;
    }

    public JCheckBox getCheckBox_2() {
        return checkBox_2;
    }

    public JCheckBox getCheckBox_3() {
        return checkBox_3;
    }

    public JTextArea getTxtrBelowTitle() {
        return txtrBelowTitle;
    }

    public TitleSettingsPanel() {
        

        super(Internationalization.getKey("Title page settings"));

        
        
        checkBox = new JCheckBox(Internationalization.getKey("Separator line above footer"));
        checkBox.setSelected(true);
        checkBox.setBounds(284, 143, 250, 23);
        add(checkBox);
        
         checkBox_1 = new JCheckBox(Internationalization.getKey("Separator line below header"));
        checkBox_1.setSelected(true);
        checkBox_1.setBounds(284, 71, 250, 23);
        add(checkBox_1);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(93, 57, 397, 2);
        add(separator);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(93, 111, 397, 2);
        add(separator_1);
        
         checkBox_2 = new JCheckBox(Internationalization.getKey("Header"));
        checkBox_2.setBounds(93, 71, 189, 23);
        add(checkBox_2);
        
         checkBox_3 = new JCheckBox(Internationalization.getKey("Footer"));
        checkBox_3.setBounds(93, 143, 189, 23);
        add(checkBox_3);
        
        JLabel lblTextBelowTitle = new JLabel(Internationalization.getKey("Text below title"));
        lblTextBelowTitle.setBounds(93, 210, 159, 19);
        add(lblTextBelowTitle);
        
        JSeparator separator_2 = new JSeparator();
        separator_2.setBounds(93, 189, 397, 2);
        add(separator_2);
        
        JSeparator separator_3 = new JSeparator();
        separator_3.setBounds(93, 297, 397, 2);
        add(separator_3);
        
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(238, 209, 243, 71);
        
        add(scrollPane);
        
        
         txtrBelowTitle = new JTextArea();
        scrollPane.setViewportView(txtrBelowTitle);

        txtrBelowTitle.setText("Online kamp\u00E1ny elemz\u00E9se");
        txtrBelowTitle.setWrapStyleWord(true);
        txtrBelowTitle.setLineWrap(true);

        txtrBelowTitle.setVisible(true);
        
        
    }
    
    @Override
    public boolean isEveryThingOk(Message message) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public SettingsChoicePanel getNewInstance() {
        return new TitleSettingsPanel();
    }
    
    

}
