package api.discount.repository;

import api.discount.domain.discountPolicy.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountPolicyRepository extends JpaRepository<Discount, Long> {
}
