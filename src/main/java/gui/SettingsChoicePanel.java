package main.java.gui;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.java.utils.Internationalization;
import main.java.utils.Message;

public abstract class SettingsChoicePanel extends JPanel{
    
    protected JLabel title;
    
    public static final int PANEL_WIDTH = 584;
    public static final int PANEL_HEIGHT = 380;
    
    
    protected SettingsChoicePanel(String titleText){
        
        setLayout(null);
        setBounds(0, 20, PANEL_WIDTH, PANEL_HEIGHT);
        
        title = new JLabel();
        title.setBounds(0, 0, PANEL_WIDTH, 30);
        title.setText(titleText);
        title.setHorizontalAlignment(SwingConstants.CENTER);
       
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 25));
        
        add(title);
        
        
    }
    
    public abstract boolean isEveryThingOk(Message message);
    
    public void displayPanel(){
        setVisible(true);
    }
    
    public void hidePanel(){
        setVisible(false);
    }
    
    

}
