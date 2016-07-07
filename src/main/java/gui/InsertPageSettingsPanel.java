package main.java.gui;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import main.java.utils.Internationalization;
import main.java.utils.Message;

public class InsertPageSettingsPanel extends SettingsChoicePanel{

    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JCheckBox chckbxSeparatorLineAbove;
    private JCheckBox chckbxSeparatorLineBelow;
    private JCheckBox chckbxHeader;
    private JCheckBox chckbxFooter;
    private JTextArea txtrCreatedBy;
    
    
    
    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }



    public JCheckBox getChckbxSeparatorLineAbove() {
        return chckbxSeparatorLineAbove;
    }



    public JCheckBox getChckbxSeparatorLineBelow() {
        return chckbxSeparatorLineBelow;
    }



    public JCheckBox getChckbxHeader() {
        return chckbxHeader;
    }



    public JCheckBox getChckbxFooter() {
        return chckbxFooter;
    }



    public JTextArea getTxtrCreatedBy() {
        return txtrCreatedBy;
    }



    protected InsertPageSettingsPanel() {
        super(Internationalization.getKey("Insert Page Settings"));
        
         chckbxSeparatorLineAbove = new JCheckBox(Internationalization.getKey("Separator line above footer"));
        chckbxSeparatorLineAbove.setSelected(true);
        chckbxSeparatorLineAbove.setBounds(284, 143, 250, 23);
        add(chckbxSeparatorLineAbove);

         chckbxSeparatorLineBelow = new JCheckBox(Internationalization.getKey("Separator line below header"));
        chckbxSeparatorLineBelow.setSelected(true);
        chckbxSeparatorLineBelow.setBounds(284, 71, 250, 23);
        add(chckbxSeparatorLineBelow);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(93, 57, 397, 2);
        add(separator);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(93, 111, 397, 2);
        add(separator_1);
        
         chckbxHeader = new JCheckBox(Internationalization.getKey("Header"));
        chckbxHeader.setBounds(93, 71, 189, 23);
        add(chckbxHeader);
        
         chckbxFooter = new JCheckBox(Internationalization.getKey("Footer"));
        chckbxFooter.setBounds(93, 143, 189, 23);
        add(chckbxFooter);
        
        JSeparator separator_2 = new JSeparator();
        separator_2.setBounds(93, 189, 397, 2);
        add(separator_2);
        
        JLabel lblCustomTextArea = new JLabel(Internationalization.getKey("Custom text area"));
        lblCustomTextArea.setBounds(93, 210, 159, 19);
        add(lblCustomTextArea);
        
        
        JSeparator separator_3 = new JSeparator();
        separator_3.setBounds(93, 297, 397, 2);
        add(separator_3);
        
        
         txtrCreatedBy = new JTextArea();
        txtrCreatedBy.setWrapStyleWord(true);
        txtrCreatedBy.setText("k\u00E9sz\u00EDtette :");
        txtrCreatedBy.setLineWrap(true);
        txtrCreatedBy.setBounds(239, 212, 241, 69);
        add(txtrCreatedBy);
        
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(238, 209, 243, 71);
        scrollPane.setViewportView(txtrCreatedBy);
        add(scrollPane);
        
        
        

        
       
    }

   

    @Override
    public boolean isEveryThingOk(Message message) {
        // TODO Auto-generated method stub
        return false;
    }



    @Override
    public SettingsChoicePanel getNewInstance() {
        
        return new InsertPageSettingsPanel();
    }

    
    
}
