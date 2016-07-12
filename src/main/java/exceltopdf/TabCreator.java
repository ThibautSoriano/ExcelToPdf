package main.java.exceltopdf;

import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import main.java.excelreader.entities.CampaignRow;
import main.java.excelreader.entities.ExcelSheet;
import main.java.utils.Utils;

public class TabCreator {

    

    public TabCreator(ExcelSheet excelSheet) {
        super();
        
    }

    public PdfPTable createTabCampaign(List<CampaignRow> campaignRows, List<String> headers,CampaignRow all,boolean[] colsToPrint,
            boolean hideEmptyLines) {

        if (colsToPrint.length < CampaignRow.MAX_COLUMNS) {
            System.err.println("Wrong tab size in createTabCampaign. Must be "
                    + CampaignRow.MAX_COLUMNS + " at least.");
            return new PdfPTable(1);
        }
        
      //one column is added because the first one has a width of 2 columns
        int colsNumber = Utils.countTrueInTab(colsToPrint);
        PdfPTable table = new PdfPTable(colsNumber+1);
        table.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        table.setWidthPercentage(100);
        

        // For the headers
        
        for (int j = 0; j < CampaignRow.MAX_COLUMNS; j++) {
            if (colsToPrint[j]) {

                Font font = new Font(FontFamily.HELVETICA, 8, Font.BOLD);
                Paragraph para = new Paragraph(headers.get(j), font);
                para.setAlignment(Element.ALIGN_CENTER);

                PdfPCell cell = new PdfPCell();
                cell.setPaddingBottom(15);

                cell.setBackgroundColor(new BaseColor(7, 167, 227));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.addElement(para);
                
                if (j==0)
                    cell.setColspan(2);

                table.addCell(cell);

            }
        }

        // For all the rows
        for (int i = 0; i < campaignRows.size(); i++) {

            if (!(hideEmptyLines && !campaignRows.get(i).isRelevant())) {

                List<String> l = campaignRows.get(i).toList();
                for (int j = 0; j < CampaignRow.MAX_COLUMNS; j++) {

                    if (colsToPrint[j]) {
                        Font font = new Font(FontFamily.HELVETICA, 8,
                                Font.UNDEFINED);
                        
                        
                        PdfPCell cell = new PdfPCell();
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        
                        cell.setPaddingBottom(10);
                        cell.setPaddingTop(0);

                        
                        if (j==0)
                            cell.setColspan(2);
                        
                       
                        
                        Paragraph p = null;
                        
                       
                        
                        
                        if (j ==0)
                             p = new Paragraph(splitFirstColumnData(l.get(0),getMaxLength(colsNumber)),font);
                        else
                            p = new Paragraph(l.get(j), font);
                        
                        if (j != 0)
                            p.setAlignment(Element.ALIGN_CENTER);

                        
                        cell.addElement(p);
                        
                        
                        table.addCell(cell);
                    }
                }
            }
        }

        // For the last line (containing the sums usually)

        List<String> a = all.toList();
        for (int j = 0; j < CampaignRow.MAX_COLUMNS; j++) {
            if (colsToPrint[j]) {

                Font font = new Font(FontFamily.HELVETICA, 8, Font.BOLD);
                Paragraph p = new Paragraph(a.get(j), font);
                if (j != 0)
                    p.setAlignment(Element.ALIGN_CENTER);

                PdfPCell cell = new PdfPCell();
                cell.addElement(p);
                cell.setPaddingBottom(10);
                cell.setPaddingTop(0);
                cell.setBackgroundColor(new BaseColor(7, 167, 227));
                
                if (j==0)
                    cell.setColspan(2);
                
                table.addCell(cell);
            }
        }

        return table;
    }
    
    
    private String splitFirstColumnData(String firstColumnData, int maxLength) {
        StringBuilder res = new StringBuilder();
        int cpt = 0;
        for (int i = 0; i< firstColumnData.length(); i++) {
            if (cpt> maxLength && firstColumnData.charAt(i) == '/') {
                res.append("/\n");
                cpt = 0;
                
            }
            else
                res.append(firstColumnData.charAt(i));
            cpt++;
            
            
        }
        return res.toString();
        
    }
    
    
    public int getMaxLength(int colsNumber){
        
        return (int) -6.67*colsNumber + 50;
    }
}
