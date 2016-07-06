package main.java.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import main.java.utils.Internationalization;
import main.java.utils.Message;

public class NavigationPanel extends JPanel implements ActionListener{

    
    private static final long serialVersionUID = -3387723564731103730L;
    
    private int totalNumberOfWindows;
    private int currentWindow = 1;
    private JButton btnNext;
    private JButton btnPrevious;
    private JButton btnValidate;
    
    private List<INavigation> navigationListeners;
    
    public static final int NAVIGATION_HEIGHT = 62;
    
    public static final int NAVIGATION_WIDTH = 584;
    
    public NavigationPanel(int totalNumberOfWindows, int currentWindow) {
        this.totalNumberOfWindows = totalNumberOfWindows;
        navigationListeners = new ArrayList<INavigation>();
        this.currentWindow = currentWindow;
        
        setLayout(null);
        setBounds(0, SettingsChoicePanel.PANEL_HEIGHT+10, NAVIGATION_WIDTH, NAVIGATION_HEIGHT);
        
        
        btnNext = new JButton(Internationalization.getKey("Next"));
        btnNext.setBounds(NAVIGATION_WIDTH - 120, 15, 100,30);
        btnNext.addActionListener(this);
        
        
        add(btnNext);
        
        
        
        btnValidate = new JButton(Internationalization.getKey("Validate"));
        btnValidate.setBounds(NAVIGATION_WIDTH - 120, 15, 100,30);
        btnValidate.addActionListener(this);
        
        
        add(btnValidate);
        
        
       
        
        
        
        btnPrevious = new JButton(Internationalization.getKey("Previous"));
        btnPrevious.setBounds(20, 15, 100,30);
        btnPrevious.addActionListener(this);
        
        add(btnPrevious);
    
        if (currentWindow==1)
            hidePreviousButton();
        else if (currentWindow == totalNumberOfWindows){
            btnNext.setVisible(false);
        }
            
        this.setVisible(true);
        
    }
    
    public void addNavigationListener(INavigation in){
        navigationListeners.add(in);
    }
    
    

  public void hidePreviousButton(){
      btnPrevious.setVisible(false);
  }

  public void showPreviousButton(){
      btnPrevious.setVisible(true);
  }



  @Override
  public void actionPerformed(ActionEvent e) {
      if (Internationalization.getKey("Previous").equals(e.getActionCommand())) {
          currentWindow--;
          
          btnNext.setVisible(true);
          btnValidate.setVisible(false);
          
          if (currentWindow == 1)
              hidePreviousButton();
          
          for (INavigation iNavigation : navigationListeners) 
            iNavigation.previous();
      }
              
      else if (Internationalization.getKey("Next").equals(e.getActionCommand())) {
          
          currentWindow++;
          btnPrevious.setVisible(true);
          if (currentWindow == totalNumberOfWindows) {
              btnNext.setVisible(false);
              btnValidate.setVisible(true);
          }
              
          
          for (INavigation iNavigation : navigationListeners) 
              iNavigation.next();
      
      }
      else
          for (INavigation iNavigation : navigationListeners) 
              iNavigation.validation();
      
  }
  
  
  public NavigationPanel getNewInstance() {
      NavigationPanel np = new NavigationPanel(totalNumberOfWindows,currentWindow);
      for (INavigation iNavigation : navigationListeners) {
        np.addNavigationListener(iNavigation);
    }
      
      return np;
              
  }
    
  
  
}
