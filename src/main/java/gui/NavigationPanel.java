package main.java.gui;

import java.awt.Dimension;

import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import main.java.utils.Internationalization;
import main.java.utils.Message;

public class NavigationPanel extends JPanel{

    
    private static final long serialVersionUID = -3387723564731103730L;
    private JButton btnNext;
    private JButton btnPrevious;
    
    public static final int NAVIGATION_HEIGHT = 62;
    
    public static final int NAVIGATION_WIDTH = 584;
    
    public NavigationPanel(ActionListener al) {
        
        
        
        setLayout(null);
        setBounds(0, SettingsChoicePanel.PANEL_HEIGHT, NAVIGATION_WIDTH, NAVIGATION_HEIGHT);
        
        
        btnNext = new JButton(Internationalization.getKey("Next"));
        btnNext.setBounds(NAVIGATION_WIDTH - 120, 15, 100,30);
        btnNext.addActionListener(al);
        
        
        add(btnNext);
        
        
        
        add(Box.createRigidArea(new Dimension(MainWindowZhengqin.WINDOW_WIDTH-300,0)));
        
        
        
        btnPrevious = new JButton(Internationalization.getKey("Previous"));
        btnPrevious.setBounds(20, 15, 100,30);
        btnPrevious.addActionListener(al);
        
        add(btnPrevious);
    
        hidePreviousButton();
        this.setVisible(true);
        
    }
    
    

  public void hidePreviousButton(){
      btnPrevious.setVisible(false);
  }

  public void showPreviousButton(){
      btnPrevious.setVisible(true);
  }
  
}
