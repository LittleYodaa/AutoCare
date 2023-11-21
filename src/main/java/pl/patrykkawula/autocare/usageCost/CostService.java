package pl.patrykkawula.autocare.usageCost;

import org.springframework.stereotype.Service;
import pl.patrykkawula.autocare.usageCost.dtos.CostViewDto;
import pl.patrykkawula.autocare.usageCost.dtos.NewCostDto;
import pl.patrykkawula.autocare.usageCost.mapperAndConverter.CostDtoMapper;

import java.util.List;

@Service
public class CostService {
    private final CostRepository costRepository;
    private final CostDtoMapper costDtoMapper;

    public CostService(CostRepository costRepository, CostDtoMapper costDtoMapper) {
        this.costRepository = costRepository;
        this.costDtoMapper = costDtoMapper;
    }

    CostViewDto saveNewCost(NewCostDto costDto) {
        Cost costToSave = costDtoMapper.map(costDto);
        Cost savedCost = costRepository.save(costToSave);
        return costDtoMapper.mapToCostViewDto(savedCost);
    }



    List<CostViewDto> findAllCosts(Long id) {
        return costRepository.getAllByCarId(id)
                .stream()
                .map(costDtoMapper::mapToCostViewDto)
                .toList();
    }
}
