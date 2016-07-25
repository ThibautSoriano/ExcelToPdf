package main.java.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSeparator;

import main.java.utils.Language;

@SuppressWarnings("serial")
public class ModulesSettingsPanel extends SettingsChoicePanel{
	private JCheckBox chckbxSummary;
	
	private JCheckBox chckbxRankings;
	
	private JCheckBox chckbxTechnical;

    private JComboBox comboBox;
	
	public JCheckBox getChckbxSummary() {
		return chckbxSummary;
	}


	public JCheckBox getChckbxRankings() {
		return chckbxRankings;
	}


	public JCheckBox getChckbxTechnical() {
		return chckbxTechnical;
	}
	
    public ModulesSettingsPanel() {
        super("Choose modules");
        
		chckbxSummary = new JCheckBox("Summary");
		chckbxSummary.setSelected(true);
		chckbxSummary.setBounds(93, 93, 189, 23);
		chckbxSummary.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!checkOneBoxFilled())
                    chckbxSummary.setSelected(true);

            }
        });
		add(chckbxSummary);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(93, 57, 397, 2);
		add(separator_1);
		
		chckbxRankings = new JCheckBox("Rankings");
		chckbxRankings.setSelected(true);
		chckbxRankings.setBounds(93, 182, 189, 23);
		chckbxRankings.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.setRankings(chckbxRankings.isSelected());
                if (!checkOneBoxFilled())
                    chckbxRankings.setSelected(true);

            }
        });
		add(chckbxRankings);
		
		chckbxTechnical = new JCheckBox("Technical");
		chckbxTechnical.setSelected(true);
		chckbxTechnical.setBounds(313, 182, 189, 23);
		chckbxTechnical.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.setTechnical(chckbxTechnical.isSelected());
                if (!checkOneBoxFilled())
                    chckbxTechnical.setSelected(true);

            }
        });
		add(chckbxTechnical);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(93, 360, 397, 2);
		add(separator_3);
		
		
		comboBox = new JComboBox();
		comboBox.setBounds(313, 295, 177, 23);
                add(comboBox);
                
                
                for (Language lang : Language.values()) {
                    comboBox.addItem(lang);
                }
                
                JLabel lblChoosePdfOutput = new JLabel("Choose pdf output language");
                lblChoosePdfOutput.setBounds(93, 295, 199, 23);
                add(lblChoosePdfOutput);
		
                JSeparator separator_4 = new JSeparator();
                separator_4.setBounds(93, 250, 397, 2);
                add(separator_4);
		
    }


	@Override
	public boolean isEveryThingOk() {
		MainWindow.setRankings(chckbxRankings.isSelected());
        MainWindow.setTechnical(chckbxTechnical.isSelected());
        MainWindow.setSummary(chckbxSummary.isSelected());
        
		return true;
	}


	@Override
	public SettingsChoicePanel getNewInstance() {
		return new ModulesSettingsPanel();
	}
	
	private boolean checkOneBoxFilled() {
        return chckbxRankings.isSelected() || chckbxTechnical.isSelected() || chckbxSummary.isSelected();

    }
	
	
	
	public Language getSelectedLanguage() {
	    return (Language) comboBox.getSelectedItem();
	}
}