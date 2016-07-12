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
import main.java.utils.Internationalization;
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
        

        super(Internationalization.getKey("General settings"));
        
        
         rdbtnBottomCenter = new JRadioButton(Internationalization.getKey("Bottom center"));
         rdbtnBottomCenter.setBounds(223, 321, 109, 23);
        add(rdbtnBottomCenter);
        rdbtnBottomCenter.setSelected(true);
        
         rdbtnBottomRight = new JRadioButton(Internationalization.getKey("Bottom right"));
         rdbtnBottomRight.setBounds(364, 321, 109, 23);
        add(rdbtnBottomRight);
        
         pagesNumGroup = new ButtonGroup();
        pagesNumGroup.add(rdbtnBottomRight);
        pagesNumGroup.add(rdbtnBottomCenter);
        
         chckbxFooterLine = new JCheckBox(Internationalization.getKey("Separator line above footer"));
        chckbxFooterLine.setSelected(true);
        chckbxFooterLine.setBounds(284, 229, 189, 23);
        add(chckbxFooterLine);
        
         chckbxHeaderLine = new JCheckBox(Internationalization.getKey("Separator line below header"));
        chckbxHeaderLine.setSelected(true);
        chckbxHeaderLine.setBounds(284, 70, 189, 23);
        add(chckbxHeaderLine);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(93, 57, 397, 2);
        add(separator);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(93, 57, 397, 2);
        add(separator_1);
        
        JSeparator separator_2 = new JSeparator();
        separator_2.setBounds(93, 208, 397, 2);
        add(separator_2);
        
        JLabel lblYourCompanyWebsite = new JLabel(Internationalization.getKey("Your company website"));
        lblYourCompanyWebsite.setBounds(93, 116, 159, 19);
        add(lblYourCompanyWebsite);
        
         txtWebsite = new JTextField();
        txtWebsite.setText("www.gemius.hu");
        txtWebsite.setColumns(10);
        txtWebsite.setBounds(284, 115, 177, 20);
        add(txtWebsite);
        
         chckbxHeader = new JCheckBox(Internationalization.getKey("Header"));
        chckbxHeader.setSelected(true);
        chckbxHeader.setBounds(93, 70, 189, 23);
        add(chckbxHeader);
        
        chckbxFooter = new JCheckBox(Internationalization.getKey("Footer"));
        chckbxFooter.setSelected(true);
        chckbxFooter.setBounds(93, 229, 189, 23);
        add(chckbxFooter);
        
        JButton btnBrowse = new JButton("");
        btnBrowse.addMouseListener(new MouseAdapter() {
               

                @Override
                public void mouseClicked(MouseEvent arg0) {
                        MainWindow.openFileChooser(FileType.LOGO, txtLogo);
                }
        });
        btnBrowse.setIcon(new ImageIcon(getClass().getResource("/Browse.png")));
        btnBrowse.setBounds(471, 158, 25, 25);
        btnBrowse.setBorder(new EmptyBorder(0, 0, 0, 0));
        
        add(btnBrowse);
        
        txtLogo = new JTextField();
        txtLogo.setColumns(10);
        txtLogo.setBounds(284, 161, 177, 20);
        add(txtLogo);
        
        JLabel lblYourCompanyLogo = new JLabel(Internationalization.getKey("Your company logo"));
        lblYourCompanyLogo.setBounds(93, 160, 168, 22);
        add(lblYourCompanyLogo);
        
        JLabel lblBottomLeftText = new JLabel(Internationalization.getKey("Bottom left text"));
        lblBottomLeftText.setBounds(93, 278, 159, 19);
        add(lblBottomLeftText);
        
        txtBottomLeftText = new JTextField();
        txtBottomLeftText.setColumns(10);
        txtBottomLeftText.setBounds(284, 277, 177, 20);
        add(txtBottomLeftText);
        
        JSeparator separator_3 = new JSeparator();
        separator_3.setBounds(93, 360, 397, 2);
        add(separator_3);
        
        
        JLabel lblPageNumerotation = new JLabel(Internationalization.getKey("Page numerotation"));
        lblPageNumerotation.setBounds(93, 323, 115, 19);
        add(lblPageNumerotation);
        
    }
    
    @Override
    public boolean isEveryThingOk(Message message) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public SettingsChoicePanel getNewInstance() {
       
        return new GeneralSettingsPanel();
    }

}
