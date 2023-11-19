package pl.patrykkawula.autocare.usageCost;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import jakarta.servlet.http.HttpServletResponse;
import pl.patrykkawula.autocare.user.dtos.CostView;


import java.io.IOException;
import java.util.List;


class PdfGenerator {
    public void generate(List<CostView> costList, HttpServletResponse response) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTitle.setSize(20);
        Paragraph firstParagraph = new Paragraph("Car exploatation costs", fontTitle);
        firstParagraph.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(firstParagraph);
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{2, 4, 2});
        table.setSpacingBefore(5);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(CMYKColor.lightGray);
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.BLACK);
        cell.setPhrase(new Phrase("Cost type", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Description", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Cost", font));
        table.addCell(cell);
        for (CostView cost : costList) {
            table.addCell(String.valueOf(cost.getCostType()));
            table.addCell(String.valueOf(cost.getDescription()));
            table.addCell(String.valueOf(cost.getCost()));
        }
        document.add(table);
        document.close();
    }
}
