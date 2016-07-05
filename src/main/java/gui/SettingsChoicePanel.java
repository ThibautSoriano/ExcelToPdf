package main.java.gui;

import javax.swing.JPanel;

import main.java.utils.Message;

public abstract class SettingsChoicePanel extends JPanel{
    
    public abstract boolean isEveryThingOk(Message message);
    
    public void displayPanel(){
        setVisible(true);
    }
    
    public void hidePanel(){
        setVisible(false);
    }
    
    

}
