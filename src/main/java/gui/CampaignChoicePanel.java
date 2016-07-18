package main.java.gui;

import java.awt.Component;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import main.java.datasdownloading.HttpDownload;
import main.java.datasdownloading.entities.CampaignHeader;
import main.java.utils.Message;

@SuppressWarnings("serial")
public class CampaignChoicePanel extends SettingsChoicePanel {

    private JTable table;
    private  List<String> campaignHeaders;
    
    
    public CampaignChoicePanel() {
        super("Campaign choice");

        
        JLabel lblSelectACampaign = new JLabel("Select a campaign");
        lblSelectACampaign.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblSelectACampaign.setBounds(54, 60, 221, 22);
        add(lblSelectACampaign);
        
        
    }
    
    public void fillTableCampaignChoice(HttpDownload htpdl){
        
        
        List<CampaignHeader> l = htpdl.getCampaignHeaders();
        
        Vector<String> columnNames = new Vector<String>();
        columnNames.addElement("<html><b>Name</b></html>");
        columnNames.addElement("<html><b>Client name</b></html>");
        
//        columnNames.addElement("Creation date");
        columnNames.addElement("<html><b>Start date</b></html>");
        columnNames.addElement("<html><b>End date</b></html>");
        
        
        campaignHeaders = new ArrayList<String>();
        
        Vector<Vector> rowData = new Vector<Vector>();
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        
        for (CampaignHeader campaignHeader : l) {
            Vector<String> row = new Vector<String>();
            row.addElement(campaignHeader.getCampaignName());
            row.addElement(campaignHeader.getClientName());
           
//            row.addElement(f.format(campaignHeader.getCreationDate()));
            row.addElement(f.format(campaignHeader.getStartDate()));
            row.addElement(f.format(campaignHeader.getEndDate()));
            rowData.addElement(row);
            
            campaignHeaders.add(campaignHeader.getCampaignID());
        }

        DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
       
        table = new JTable(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(110);
        table.getColumnModel().getColumn(2).setPreferredWidth(30);
        table.getColumnModel().getColumn(3).setPreferredWidth(30);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        
       
       
        
        class HeaderRenderer implements TableCellRenderer {

            DefaultTableCellRenderer renderer;

            public HeaderRenderer(JTable table) {
                renderer = (DefaultTableCellRenderer)
                    table.getTableHeader().getDefaultRenderer();
                renderer.setHorizontalAlignment(JLabel.CENTER);
            }

            @Override
            public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int col) {
                return renderer.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, col);
            }
        }
        
        table.getTableHeader().setDefaultRenderer(new HeaderRenderer(table));
        
        table.getColumnModel().getColumn(1).setCellRenderer(renderer);
        table.getColumnModel().getColumn(2).setCellRenderer(renderer);
        table.getColumnModel().getColumn(3).setCellRenderer(renderer);
        
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
  
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(46, 99, 495, 259);
        scrollPane.add(table);
        scrollPane.setViewportView(table);
        add(scrollPane);

       
        
        
    }
    
    
    


    private CampaignChoicePanel(JTable table) {
        super("Campaign selection");
        this.table = table;
        
        
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
        
        return table.getSelectedRow() > -1;
    }

    @Override
    public SettingsChoicePanel getNewInstance() {
        return new CampaignChoicePanel(this.table);
    }
    
    public static void main(String[] args) {
        
        try {
            // set for file chooser look
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            //
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        
        JFrame frame = new JFrame("a");
        frame.setSize(600,500);
        frame.getContentPane().setLayout(null);
//        frame.getContentPane().add(new CampaignChoicePanel());
        frame.setVisible(true);
        
        
    }
    
    public String getSelectedId(){
        return campaignHeaders.get(table.getSelectedRow());
    }

}
