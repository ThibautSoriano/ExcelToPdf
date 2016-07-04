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

    private ExcelSheet excelSheet;

    public TabCreator(ExcelSheet excelSheet) {
        super();
        this.excelSheet = excelSheet;
    }

    public PdfPTable createTabCampaign(boolean[] colsToPrint,
            boolean hideEmptyLines) {

        if (colsToPrint.length < CampaignRow.MAX_COLUMNS) {
            System.err.println("Wrong tab size in createTabCampaign. Must be "
                    + CampaignRow.MAX_COLUMNS + " at least.");
            return new PdfPTable(1);
        }
        List<CampaignRow> campaignRows = excelSheet.getCampaignRows();
        PdfPTable table = new PdfPTable(Utils.countTrueInTab(colsToPrint));
        table.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        table.setWidthPercentage(100);

        // For the headers
        List<String> labels = excelSheet.getColumsLabels();
        for (int j = 0; j < CampaignRow.MAX_COLUMNS; j++) {
            if (colsToPrint[j]) {

                Font font = new Font(FontFamily.HELVETICA, 8, Font.BOLD);
                Paragraph para = new Paragraph(labels.get(j), font);
                para.setAlignment(Element.ALIGN_CENTER);

                PdfPCell cell = new PdfPCell();
                cell.setPaddingBottom(15);

                cell.setBackgroundColor(new BaseColor(7, 167, 227));
                cell.addElement(para);

                table.addCell(cell);

            }
        }

        // For all the rows
        for (int i = 0; i < campaignRows.size(); i++) {

            if (!(hideEmptyLines && campaignRows.get(i).isEmpty())) {

                List<String> l = campaignRows.get(i).toList();
                for (int j = 0; j < CampaignRow.MAX_COLUMNS; j++) {

                    if (colsToPrint[j]) {
                        Font font = new Font(FontFamily.HELVETICA, 8,
                                Font.UNDEFINED);
                        Paragraph p = new Paragraph(l.get(j), font);
                        if (j != 0)
                            p.setAlignment(Element.ALIGN_CENTER);

                        PdfPCell cell = new PdfPCell();
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.addElement(p);
                        cell.setPaddingBottom(10);
                        cell.setPaddingTop(0);

                        table.addCell(cell);
                    }
                }
            }
        }

        // For the last line (containing the sums usually)

        List<String> all = excelSheet.getAll().toList();
        for (int j = 0; j < CampaignRow.MAX_COLUMNS; j++) {
            if (colsToPrint[j]) {

                Font font = new Font(FontFamily.HELVETICA, 8, Font.BOLD);
                Paragraph p = new Paragraph(all.get(j), font);
                if (j != 0)
                    p.setAlignment(Element.ALIGN_CENTER);

                PdfPCell cell = new PdfPCell();
                cell.addElement(p);
                cell.setPaddingBottom(10);
                cell.setPaddingTop(0);
                cell.setBackgroundColor(new BaseColor(7, 167, 227));
                table.addCell(cell);
            }
        }

        return table;
    }
}
