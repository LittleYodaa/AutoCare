package pl.patrykkawula.autocare.usageCost;

import org.springframework.stereotype.Service;
import pl.patrykkawula.autocare.usageCost.dtos.CostViewDto;
import pl.patrykkawula.autocare.usageCost.dtos.NewCostDto;
import pl.patrykkawula.autocare.user.dtos.CostView;

import java.util.List;

@Service
class CostService {
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


    //todo
    //exception from projection(CarNotFound)
    List<CostView> findAllCosts(Long id) {
        return costRepository.getAllByCarId(id);
    }
}
