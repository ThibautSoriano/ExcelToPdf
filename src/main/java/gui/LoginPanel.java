package main.java.gui;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.java.datasdownloading.HttpDownload;
import main.java.utils.SaveSettings;

@SuppressWarnings("serial")
public class LoginPanel extends SettingsChoicePanel {

    private JTextField txtLogin;
    private JPasswordField txtPassword;
    private JCheckBox chckbxRememberTheLogin;

    

    public LoginPanel() {
        super("Login");

        

        JSeparator separator = new JSeparator();
        separator.setBounds(93, 57, 397, 2);
        add(separator);

        JSeparator separator_3 = new JSeparator();
        separator_3.setBounds(93, 297, 397, 2);
        add(separator_3);

        JLabel lblLogin = new JLabel("Login name");
        lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogin.setBounds(200, 106, 200, 19);
        add(lblLogin);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
        lblPassword.setBounds(200, 167, 200, 19);
        add(lblPassword);

        txtLogin = new JTextField();
        txtLogin.setBounds(211, 136, 173, 20);
        add(txtLogin);
        
        if (SaveSettings.loginHasToBeRemembered())
            txtLogin.setText(SaveSettings.getLogin());
        
        
        txtLogin.setColumns(10);

        txtPassword = new JPasswordField();
        txtPassword.setColumns(10);
        txtPassword.setBounds(211, 197, 173, 20);
        add(txtPassword);
        
        
        chckbxRememberTheLogin = new JCheckBox("Remember the login");
        chckbxRememberTheLogin.setBounds(211, 247, 173, 23);
        chckbxRememberTheLogin.setOpaque(false);
        chckbxRememberTheLogin.setSelected(true);
        add(chckbxRememberTheLogin);
        

    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isEveryThingOk() {
        String login = txtLogin.getText();
        String password = txtPassword.getText();

        if ("".equals(login)) {
            JOptionPane.showMessageDialog(null, "No login name given", "ERROR",
                    JOptionPane.ERROR_MESSAGE);

            return false;
        } else if ("".equals(password)) {
            JOptionPane.showMessageDialog(null, "No password given", "ERROR",
                    JOptionPane.ERROR_MESSAGE);

            return false;
        }

        try {
            
            SaveSettings.changeLoginHasToBeRemembered(chckbxRememberTheLogin.isSelected());
            if (chckbxRememberTheLogin.isSelected()) {
                SaveSettings.saveLoginName(txtLogin.getText());
            }
               
            
            if (MainWindow.getSession() == null || !MainWindow.getSession().isSameLogin(login, password)) {
                HttpDownload htpdl = new HttpDownload(login, password);
                MainWindow.setSession(htpdl);
            }
            

            return true;
        } catch (Exception e) {
//            e.printStackTrace();
           
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    @Override
    public SettingsChoicePanel getNewInstance() {
        // TODO Auto-generated method stub
        return new LoginPanel();
    }

}
