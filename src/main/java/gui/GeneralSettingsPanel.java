package main.java.gui;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.java.utils.FileType;
import main.java.utils.Message;

public class GeneralSettingsPanel extends SettingsChoicePanel{

    
    
    private JTextField txtLogo;
    private JTextField txtBottomLeftText;
    private JRadioButton rdbtnBottomCenter;
    private JRadioButton rdbtnBottomRight;
    private ButtonGroup pagesNumGroup;
    private JCheckBox chckbxFooterLine;
    private JCheckBox chckbxHeaderLine;
    private JTextField txtWebsite;
    private JCheckBox chckbxHeader;
    private JCheckBox chckbxFooter;
    
    
    
    public JTextField getTxtLogo() {
        return txtLogo;
    }

    public JTextField getTxtBottomLeftText() {
        return txtBottomLeftText;
    }

    public JRadioButton getRdbtnBottomCenter() {
        return rdbtnBottomCenter;
    }

    public JRadioButton getRdbtnBottomRight() {
        return rdbtnBottomRight;
    }

    public ButtonGroup getPagesNumGroup() {
        return pagesNumGroup;
    }

    public JCheckBox getChckbxFooterLine() {
        return chckbxFooterLine;
    }

    public JCheckBox getChckbxHeaderLine() {
        return chckbxHeaderLine;
    }

    public JTextField getTxtWebsite() {
        return txtWebsite;
    }

    public JCheckBox getChckbxHeader() {
        return chckbxHeader;
    }

    public JCheckBox getChckbxFooter() {
        return chckbxFooter;
    }

    public GeneralSettingsPanel() {
        

        super("General settings");
        
 
        JLabel lblPageNumerotation = new JLabel("Page numerotation");
        lblPageNumerotation.setBounds(93, 75, 115, 19);
        add(lblPageNumerotation);
        
         rdbtnBottomCenter = new JRadioButton("Bottom center");
        rdbtnBottomCenter.setBounds(223, 73, 109, 23);
        add(rdbtnBottomCenter);
        rdbtnBottomCenter.setSelected(true);
        
         rdbtnBottomRight = new JRadioButton("Bottom right");
        rdbtnBottomRight.setBounds(385, 68, 109, 23);
        add(rdbtnBottomRight);
        
         pagesNumGroup = new ButtonGroup();
        pagesNumGroup.add(rdbtnBottomRight);
        pagesNumGroup.add(rdbtnBottomCenter);
        
         chckbxFooterLine = new JCheckBox("Separator line above footer");
        chckbxFooterLine.setSelected(true);
        chckbxFooterLine.setBounds(284, 269, 189, 23);
        add(chckbxFooterLine);
        
         chckbxHeaderLine = new JCheckBox("Separator line below header");
        chckbxHeaderLine.setSelected(true);
        chckbxHeaderLine.setBounds(284, 119, 189, 23);
        add(chckbxHeaderLine);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(93, 105, 397, 2);
        add(separator);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(93, 57, 397, 2);
        add(separator_1);
        
        JSeparator separator_2 = new JSeparator();
        separator_2.setBounds(93, 257, 397, 2);
        add(separator_2);
        
        JLabel lblYourCompanyWebsite = new JLabel("Your company website");
        lblYourCompanyWebsite.setBounds(93, 165, 159, 19);
        add(lblYourCompanyWebsite);
        
         txtWebsite = new JTextField();
        txtWebsite.setText("www.gemius.hu");
        txtWebsite.setColumns(10);
        txtWebsite.setBounds(284, 164, 177, 20);
        add(txtWebsite);
        
         chckbxHeader = new JCheckBox("Header");
        chckbxHeader.setSelected(true);
        chckbxHeader.setBounds(93, 119, 189, 23);
        add(chckbxHeader);
        
        chckbxFooter = new JCheckBox("Footer");
        chckbxFooter.setSelected(true);
        chckbxFooter.setBounds(93, 269, 189, 23);
        add(chckbxFooter);
        
        JButton btnBrowse = new JButton("");
        btnBrowse.addMouseListener(new MouseAdapter() {
               

                @Override
                public void mouseClicked(MouseEvent arg0) {
                        MainWindow.openFileChooser(FileType.LOGO, txtLogo);
                }
        });
        btnBrowse.setIcon(new ImageIcon(".\\src\\main\\resources\\Browse.png"));
        btnBrowse.setBounds(471, 207, 25, 25);
        btnBrowse.setBorder(new EmptyBorder(0, 0, 0, 0));
        
        add(btnBrowse);
        
        txtLogo = new JTextField();
        txtLogo.setColumns(10);
        txtLogo.setBounds(284, 210, 177, 20);
        add(txtLogo);
        
        JLabel lblYourCompanyLogo = new JLabel("Your company logo");
        lblYourCompanyLogo.setBounds(93, 209, 168, 22);
        add(lblYourCompanyLogo);
        
        JLabel lblBottomLeftText = new JLabel("Bottom left text");
        lblBottomLeftText.setBounds(93, 318, 159, 19);
        add(lblBottomLeftText);
        
        txtBottomLeftText = new JTextField();
        txtBottomLeftText.setColumns(10);
        txtBottomLeftText.setBounds(284, 317, 177, 20);
        add(txtBottomLeftText);
        
        JSeparator separator_3 = new JSeparator();
        separator_3.setBounds(93, 351, 397, 2);
        add(separator_3);
    }
    
    @Override
    public boolean isEveryThingOk(Message message) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public SettingsChoicePanel getNewInstance() {
       
        return new GeneralSettingsPanel();
    }

}
