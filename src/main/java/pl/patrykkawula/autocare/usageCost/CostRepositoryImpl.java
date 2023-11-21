package pl.patrykkawula.autocare.usageCost;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import pl.patrykkawula.autocare.usageCost.mapperAndConverter.CostTypeEnumConverter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CostRepositoryImpl implements CostRepositoryCustom{
    private final CostTypeEnumConverter costTypeEnumConverter;
    public CostRepositoryImpl(CostTypeEnumConverter costTypeEnumConverter) {
        this.costTypeEnumConverter = costTypeEnumConverter;
    }
    @PersistenceContext
    EntityManager em;

    @Override
    public List<Cost> findCostByCostTypeAndDate(Long id, String costType, LocalDate startDate, LocalDate endDate) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cost> cq = cb.createQuery(Cost.class);

        Root<Cost> cost = cq.from(Cost.class);
        List<Predicate> predicates = new ArrayList<>();

        if (costType != null) {
            predicates.add(cb.equal(cost.get("costType"), costTypeEnumConverter.convert(costType)));
        }
        if (startDate != null) {
            predicates.add(cb.greaterThanOrEqualTo(cost.get("dateOfAdd"), startDate));
        }
        if (endDate != null) {
            predicates.add(cb.lessThanOrEqualTo(cost.get("dateOfAdd"), endDate));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }
}
