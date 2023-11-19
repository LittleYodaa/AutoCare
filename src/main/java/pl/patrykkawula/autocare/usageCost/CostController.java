package pl.patrykkawula.autocare.usageCost;

import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.patrykkawula.autocare.usageCost.dtos.NewCostDto;
import pl.patrykkawula.autocare.usageCost.dtos.CostViewDto;
import pl.patrykkawula.autocare.user.dtos.CostView;

import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cost")
public class CostController {
    private final CostService costService;

    CostController(CostService costService) {
        this.costService = costService;
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
    ResponseEntity<List<CostView>> getAllCosts(@PathVariable Long id) {
        List<CostView> allCosts = costService.findAllCosts(id);
        return ResponseEntity.ok(allCosts);
    }

    @GetMapping("/pdf/{id}")
    public void generatePdfFile(@PathVariable Long id, HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=student" + currentDateTime + ".pdf";
        List<CostView> allCosts = costService.findAllCosts(id);
        PdfGenerator generator = new PdfGenerator();
        generator.generate(allCosts, response);
    }
}
