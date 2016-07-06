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
    
    
    public NavigationPanel(ActionListener al) {
        
        
        
        setLayout(null);
        setBounds(41, 317, 716, 109);
        
        
        btnNext = new JButton(Internationalization.getKey("Next"));
        btnNext.setBounds(606, 43, 100, 23);
        btnNext.addActionListener(al);
        
        
        add(btnNext);
        
        
        
        add(Box.createRigidArea(new Dimension(MainWindowZhengqin.WINDOW_WIDTH-300,0)));
        
        
        
        btnPrevious = new JButton(Internationalization.getKey("Previous"));
        btnPrevious.setBounds(32, 43, 100, 23);
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
