package pl.patrykkawula.autocare.usageCost.pdfPreparation;

import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import pl.patrykkawula.autocare.usageCost.dtos.PdfCostDto;

import java.io.IOException;
import java.util.List;

@Service
class PdfTemplate {
    public void generatePdfTemplate(List<PdfCostDto> costList, HttpServletResponse response, String pdfName) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTitle.setSize(20);
        Paragraph firstParagraph = new Paragraph(pdfName, fontTitle);
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
        for (PdfCostDto cost : costList) {
            table.addCell(String.valueOf(cost.costType()));
            table.addCell(String.valueOf(cost.description()));
            table.addCell(String.valueOf(cost.cost()));
        }
        document.add(table);
        document.close();
    }
}
