package api.discount.repository;

import api.discount.domain.Item;
import api.discount.domain.discountPolicy.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountPolicyRepository extends JpaRepository<Discount, Long> {

    @Query("select d " +
            " from Discount d " +
            " join fetch d.discountCondition c " +
            " join fetch d.discountType t " +
            " where c.item = :item")
    List<Discount> findDiscountPolicyByItem(@Param("item") Item item);

}
