package api.discount.repository;

import api.discount.domain.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {
}
