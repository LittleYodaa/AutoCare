package pl.patrykkawula.autocare.usageCost.pdfPreparation;

import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import pl.patrykkawula.autocare.usageCost.mapperAndConverter.CostDtoMapper;
import pl.patrykkawula.autocare.usageCost.CostRepositoryImpl;
import pl.patrykkawula.autocare.usageCost.dtos.PdfCostDto;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@Service
public class PdfGenerator {
    private final static String allCost = "All car exploitation cost";
    private final PdfTemplate pdfTemplate;
    private final CostDtoMapper costDtoMapper;
    private final CostRepositoryImpl costRepositoryImpl;

    public PdfGenerator(CostRepositoryImpl costRepositoryImpl, PdfTemplate pdfTemplate, CostDtoMapper costDtoMapper) {
        this.costRepositoryImpl = costRepositoryImpl;
        this.pdfTemplate = pdfTemplate;
        this.costDtoMapper = costDtoMapper;
    }

    public void getCosts(Long id, String CostType, LocalDate startDate, LocalDate endDate, HttpServletResponse response) throws DocumentException, IOException {
        List<PdfCostDto> pdfCostDto = costRepositoryImpl.findCostByCostTypeAndDate(id, CostType, startDate, endDate)
                .stream()
                .map(costDtoMapper::mapToPdfCostDto)
                .toList();
        pdfTemplate.generatePdfTemplate(pdfCostDto, response, allCost);
    }
}
