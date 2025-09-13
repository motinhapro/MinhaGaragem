package com.edumota.minhagaragem.repositories.specification;

import com.edumota.minhagaragem.domain.Car;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class CarSpecifications {

    public static Specification<Car> belongsToUser(UUID userId) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("user").get("id"), userId);
        };
    }

    public static Specification<Car> hasBrand(String brand) {
        return (root, query, criteriaBuilder) -> {

            if (brand == null || brand.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(criteriaBuilder.lower(root.get("brand")), brand.toLowerCase());
        };
    }

    public static Specification<Car> modelLike(String model) {
        return (root, query, criteriaBuilder) -> {
            if (model == null || model.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("model")), "%" + model.toLowerCase() + "%");
        };
    }

    public static Specification<Car> byYearRange(Integer minYear, Integer maxYear) {
        return (root, query, criteriaBuilder) -> {

            if(minYear != null && maxYear != null) {
                return criteriaBuilder.between(root.get("year"), minYear, maxYear);
            }

            if(minYear != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("year"), minYear);
            }

            if (maxYear != null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("year"), maxYear);
            }

            return criteriaBuilder.conjunction();
        };
    }
}
