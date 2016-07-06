package main.java.gui;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;

import main.java.utils.Message;

public class GeneralSettingsPanel extends SettingsChoicePanel{

    
    
    public GeneralSettingsPanel() {
        

        super("General settings");
        
        
        
        
        JLabel lblPageNumerotation = new JLabel("Page numerotation");
        lblPageNumerotation.setBounds(27, 34, 115, 19);
        add(lblPageNumerotation);
        
        JRadioButton rdbtnBottomCenter = new JRadioButton("Bottom center");
        rdbtnBottomCenter.setBounds(157, 32, 109, 23);
        add(rdbtnBottomCenter);
        rdbtnBottomCenter.setSelected(true);
        
        JRadioButton rdbtnBottomRight = new JRadioButton("Bottom right");
        rdbtnBottomRight.setBounds(319, 27, 109, 23);
        add(rdbtnBottomRight);
        
        JCheckBox chckbxFooterLine = new JCheckBox("Separator line above footer");
        chckbxFooterLine.setSelected(true);
        chckbxFooterLine.setBounds(218, 82, 189, 23);
        add(chckbxFooterLine);
        
        JCheckBox chckbxHeaderLine = new JCheckBox("Separator line below header");
        chckbxHeaderLine.setSelected(true);
        chckbxHeaderLine.setBounds(27, 82, 189, 23);
        add(chckbxHeaderLine);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(27, 64, 397, 2);
        add(separator);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(27, 21, 397, 2);
        add(separator_1);}
    
    @Override
    public boolean isEveryThingOk(Message message) {
        // TODO Auto-generated method stub
        return false;
    }

}
