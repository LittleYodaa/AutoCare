package pl.patrykkawula.autocare.usageCost;

import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.patrykkawula.autocare.usageCost.dtos.CostViewDto;
import pl.patrykkawula.autocare.usageCost.dtos.NewCostDto;
import pl.patrykkawula.autocare.usageCost.pdfPreparation.PdfGenerator;

import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cost")
public class CostController {
    private final CostService costService;
    private final PdfGenerator pdfGenerator;

    CostController(CostService costService, PdfGenerator pdfGenerator) {
        this.costService = costService;
        this.pdfGenerator = pdfGenerator;
    }

    @PostMapping
    ResponseEntity<?> addNewCostToCar(@RequestBody NewCostDto costDto) {
        CostViewDto costViewDto = costService.saveNewCost(costDto);
        URI savedCostUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(costViewDto.id())
                .toUri();
        log.info("Add new cost {}", costViewDto);
        return ResponseEntity.created(savedCostUri).build();
    }

    @GetMapping("/{id}")
    ResponseEntity<List<CostViewDto>> getAllCosts(@PathVariable Long id) {
        List<CostViewDto> allCosts = costService.findAllCosts(id);
        return ResponseEntity.ok(allCosts);
    }

    @GetMapping("/pdf/{id}")
    public void generatePdfWithAllCost(@PathVariable Long id,
                                       @RequestParam(required = false) String costType,
                                       @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                       @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                       HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=student" + currentDateTime + ".pdf";
        pdfGenerator.getCosts(id, costType, startDate, endDate, response);
    }
}
