package pl.patrykkawula.autocare.usageCost.mapperAndConverter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.patrykkawula.autocare.usageCost.Cost;

@Component
public class CostTypeEnumConverter implements Converter<String, Cost.CostType> {

    @Override
    public Cost.CostType convert(String value) {
        return Cost.CostType.valueOf(value);
    }
}
