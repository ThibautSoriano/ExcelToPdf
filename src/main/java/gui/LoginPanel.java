package main.java.gui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.java.datasdownloading.HttpDownload;
import main.java.utils.Message;

public class LoginPanel extends SettingsChoicePanel{

    
    private HttpDownload htpdl;
    private JTextField txtLogin;
    private JPasswordField txtPassword;
    
    private CampaignChoicePanel ccp;
    
    public LoginPanel(CampaignChoicePanel ccp) {
        super("Login");
        
        this.ccp = ccp;
        
        JSeparator separator = new JSeparator();
        separator.setBounds(93, 57, 397, 2);
        add(separator);
        
        JSeparator separator_3 = new JSeparator();
        separator_3.setBounds(93, 297, 397, 2);
        add(separator_3);
        
        JLabel lblLogin = new JLabel("Login name");
        lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogin.setBounds(211, 106, 173, 19);
        add(lblLogin);
        
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
        lblPassword.setBounds(211, 167, 173, 19);
        add(lblPassword);
        
         txtLogin = new JTextField();
        txtLogin.setBounds(211, 136, 173, 20);
        add(txtLogin);
        txtLogin.setColumns(10);
        
         txtPassword = new JPasswordField();
        txtPassword.setColumns(10);
        txtPassword.setBounds(211, 197, 173, 20);
        add(txtPassword);
        
    }
    
    
   
    @Override
    public boolean isEveryThingOk(Message message) {
        try {
           
            HttpDownload htpdl = new HttpDownload(txtLogin.getText(), txtPassword.getText());
            MainWindow.setSession(htpdl);
            ccp.fillTableCampaignChoice(htpdl);
            return true;
        } catch (Exception e) {
           
           JOptionPane.showMessageDialog(null,  e.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }

    @Override
    public SettingsChoicePanel getNewInstance() {
        // TODO Auto-generated method stub
        return null;
    }
    

}
