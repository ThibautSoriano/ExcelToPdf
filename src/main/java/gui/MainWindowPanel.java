package main.java.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.java.utils.FileType;
import main.java.utils.Internationalization;
import main.java.utils.Message;
import main.java.utils.Utils;

public class MainWindowPanel extends SettingsChoicePanel{

    
    private JTextField txtExcel;
    private JTextField txtLogo;
    
    public MainWindowPanel() {
        
        super(Internationalization.getKey("Excel to pdf converter"));
        
       
        
        
       
        
        
      
        JLabel lblChooseExcel = new JLabel(Internationalization.getKey("Choose an excel file"));
        lblChooseExcel.setHorizontalAlignment(SwingConstants.CENTER);
        lblChooseExcel.setBounds(30, 119, 389, 22);
        add(lblChooseExcel);
       
        lblChooseExcel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        
        txtExcel = new JTextField();
        txtExcel.setBounds(30, 168, 389, 20);
        txtExcel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        txtExcel.setColumns(10);
        
        add(txtExcel);
        
       
        
        
        
        
        JButton btnExcel = new JButton(Internationalization.getKey("Browse"));
      btnExcel.setBounds(624, 35, 119, 23);
     add(btnExcel);          
      btnExcel.addMouseListener(new MouseAdapter() {
              @Override
              public void mouseClicked(MouseEvent e) {
                      MainWindowZhengqin.openFileChooser(FileType.EXCEL,txtExcel);
              }
      });
      btnExcel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
      
      
        JLabel lblLogo = new JLabel(Internationalization.getKey("Specify your logo (optional)"));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogo.setBounds(30, 236, 389, 22);
        add(lblLogo);
        lblLogo.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        
        txtLogo = new JTextField();
        txtLogo.setBounds(30, 284, 389, 20);
        add(txtLogo);
        txtLogo.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        txtLogo.setColumns(10);
        
        JButton btnLogo = new JButton(Internationalization.getKey("Browse"));
        btnLogo.setBounds(444, 165, 119, 23);
        add(btnLogo);
        btnLogo.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        
        
        btnLogo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    MainWindowZhengqin.openFileChooser(FileType.LOGO,txtLogo);
            }
    });
        


    }
    
    
    
    
    
    
   



    @Override
    public boolean isEveryThingOk(Message message) {
        // TODO Auto-generated method stub
        return false;
    }

}
