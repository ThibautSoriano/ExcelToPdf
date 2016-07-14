package main.java.gui;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import main.java.datasdownloading.HttpDownload;
import main.java.datasdownloading.entities.CampaignHeader;
import main.java.utils.Message;

@SuppressWarnings("serial")
public class CampaignChoicePanel extends SettingsChoicePanel {

    private JTable table;

    public CampaignChoicePanel() {
        super("Campaign choice");

        
        HttpDownload htpdl = new HttpDownload();
        List<CampaignHeader> l = htpdl.getCampaignHeaders();
        
        Vector<String> columnNames = new Vector<String>();
        columnNames.addElement("Name");
        columnNames.addElement("Client name");
        columnNames.addElement("Status");
        columnNames.addElement("Creation date");
        columnNames.addElement("Start date");
        columnNames.addElement("End date");
        
        

        Vector<Vector> rowData = new Vector<Vector>();
        SimpleDateFormat f = new SimpleDateFormat("dd/mm/yyyy");
        
        for (CampaignHeader campaignHeader : l) {
            Vector<String> row = new Vector<String>();
            row.addElement(campaignHeader.getCampaignName());
            row.addElement(campaignHeader.getClientName());
            row.addElement(campaignHeader.getCampaignStatus().toString());
            row.addElement(f.format(campaignHeader.getCreationDate()));
            row.addElement(f.format(campaignHeader.getStartDate()));
            row.addElement(f.format(campaignHeader.getEndDate()));
            rowData.addElement(row);
        }

        table = new JTable(rowData, columnNames);


        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(46, 99, 495, 259);
        scrollPane.add(table);
        scrollPane.setViewportView(table);
        add(scrollPane);

        JLabel lblSelectACampaign = new JLabel("Select a campaign");
        lblSelectACampaign.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblSelectACampaign.setBounds(54, 60, 221, 22);
        add(lblSelectACampaign);
        
        
        
    }

    @Override
    public boolean isEveryThingOk(Message message) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public SettingsChoicePanel getNewInstance() {
        // TODO Auto-generated method stub
        return null;
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("a");
        frame.setSize(600,500);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().add(new CampaignChoicePanel());
        frame.setVisible(true);
        
        
    }

}
