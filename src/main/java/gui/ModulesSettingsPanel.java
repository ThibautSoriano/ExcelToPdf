package main.java.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class ModulesSettingsPanel extends SettingsChoicePanel{
	private JCheckBox chckbxSummary;
	
	private JCheckBox chckbxRankings;
	
	private JCheckBox chckbxTechnical;
	
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
		chckbxSummary.setBounds(196, 94, 189, 23);
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
		chckbxRankings.setBounds(196, 159, 189, 23);
		chckbxRankings.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!checkOneBoxFilled())
                    chckbxRankings.setSelected(true);

            }
        });
		add(chckbxRankings);
		
		chckbxTechnical = new JCheckBox("Technical");
		chckbxTechnical.setSelected(true);
		chckbxTechnical.setBounds(196, 221, 189, 23);
		chckbxTechnical.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!checkOneBoxFilled())
                    chckbxTechnical.setSelected(true);

            }
        });
		add(chckbxTechnical);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(93, 360, 397, 2);
		add(separator_3);
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
}