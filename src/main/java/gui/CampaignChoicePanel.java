package main.java.gui;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Font;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import main.java.datasdownloading.HttpDownload;
import main.java.datasdownloading.entities.CampaignHeader;

@SuppressWarnings("serial")
public class CampaignChoicePanel extends SettingsChoicePanel {

    private JTable table;
    private List<String> campaignHeaders;
    private JCheckBox chckbxRankings;
    private JCheckBox chckbxTechnical;

    private HttpDownload htpdl;
    private JScrollPane scrollPane;
    private JTextField txtFilter;

    public JCheckBox getChckbxRankings() {
        return chckbxRankings;
    }

    public JCheckBox getChckbxTechnical() {
        return chckbxTechnical;
    }

    public CampaignChoicePanel() {
        super("Campaign choice");

        JLabel lblSelectACampaign = new JLabel("Select a campaign");
        lblSelectACampaign.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblSelectACampaign.setBounds(54, 60, 221, 22);
        add(lblSelectACampaign);

        chckbxRankings = new JCheckBox("Rankings");
        chckbxRankings.setSelected(true);
        chckbxRankings.setBounds(178, 334, 97, 23);
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
        chckbxTechnical.setBounds(368, 334, 97, 23);
        chckbxTechnical.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!checkOneBoxFilled())
                    chckbxTechnical.setSelected(true);

            }
        });
        add(chckbxTechnical);

        JLabel loading = new JLabel("Loading...");
        loading.setFont(new Font("Tahoma", Font.BOLD, 14));
        loading.setBounds(200, 150, 100, 30);
        add(loading);

        addAncestorListener(new AncestorListener() {

            @Override
            public void ancestorRemoved(AncestorEvent event) {

            }

            @Override
            public void ancestorMoved(AncestorEvent event) {

            }

            @Override
            public void ancestorAdded(AncestorEvent event) {

                if (!(htpdl == null)
                        && htpdl.isSameLogin(MainWindow.getSession()))
                    return;

                if (scrollPane != null)
                    remove(scrollPane);
                loading.setVisible(true);

                // Thread In order to display the window before it is finished
                Thread t = new Thread(new Runnable() {

                    @Override
                    public void run() {

                        htpdl = MainWindow.getSession();
                        fillTableCampaignChoice(htpdl);
                        loading.setVisible(false);

                    }
                });
                t.start();
            }

        });

        txtFilter = new JTextField();
        txtFilter.setBounds(310, 61, 231, 22);
        add(txtFilter);
        txtFilter.setColumns(10);

    }

    private boolean checkOneBoxFilled() {
        return chckbxRankings.isSelected() || chckbxTechnical.isSelected();

    }

    public void fillTableCampaignChoice(HttpDownload htpdl) {

        if (table != null)
            scrollPane.remove(table);

        List<CampaignHeader> l = htpdl.getCampaignHeaders();

        Vector<String> columnNames = new Vector<String>();
        columnNames.addElement("<html><b>Name</b></html>");
        columnNames.addElement("<html><b>Client name</b></html>");

        // columnNames.addElement("Creation date");
        columnNames.addElement("<html><b>Start date</b></html>");
        columnNames.addElement("<html><b>End date</b></html>");

        campaignHeaders = new ArrayList<String>();

        Vector<Vector> rowData = new Vector<Vector>();
        SimpleDateFormat f = new SimpleDateFormat("yyyy.MM.dd");

        for (CampaignHeader campaignHeader : l) {
            Vector<String> row = new Vector<String>();
            row.addElement(campaignHeader.getCampaignName());
            row.addElement(campaignHeader.getClientName());

            row.addElement(f.format(campaignHeader.getCreationDate()));
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
                renderer = (DefaultTableCellRenderer) table.getTableHeader()
                        .getDefaultRenderer();
                renderer.setHorizontalAlignment(JLabel.CENTER);
            }

            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row,
                    int col) {
                return renderer.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, col);
            }
        }

        table.getTableHeader().setDefaultRenderer(new HeaderRenderer(table));

        table.getColumnModel().getColumn(1).setCellRenderer(renderer);
        table.getColumnModel().getColumn(2).setCellRenderer(renderer);
        table.getColumnModel().getColumn(3).setCellRenderer(renderer);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.setAutoCreateRowSorter(true);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(46, 99, 495, 217);

        scrollPane.add(table);
        scrollPane.setViewportView(table);
        add(scrollPane);

        revalidate();
        repaint();
        scrollPane.setVisible(true);

        // txtFilter.addChan

        txtFilter.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent arg0) {
                String filter = txtFilter.getText();
                
                if (filter.isEmpty()) {
                    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
                            ((DefaultTableModel) table.getModel()));
                    sorter.setRowFilter(RowFilter.regexFilter(".*"));
                    table.setRowSorter(sorter);
                    
                }
                else {
                    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
                            ((DefaultTableModel) table.getModel()));
                    sorter.setRowFilter(RowFilter.regexFilter(
                            "(?i)" + filter));
                    table.setRowSorter(sorter);
                }

            }
        });

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
    public boolean isEveryThingOk() {

        MainWindow.setRankings(chckbxRankings.isSelected());
        MainWindow.setTechnical(chckbxTechnical.isSelected());

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
        frame.setSize(600, 500);
        frame.getContentPane().setLayout(null);
        // frame.getContentPane().add(new CampaignChoicePanel());
        frame.setVisible(true);

    }

    public String getSelectedId() {
        return campaignHeaders
                .get(table.convertRowIndexToModel(table.getSelectedRow()));
    }

}
