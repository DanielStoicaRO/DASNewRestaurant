package das.project.repository;

import das.project.modelEntity.Category;
import das.project.modelEntity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {

    Optional<Delivery> findByFoodIgnoreCase(String food);

    Optional<List<Delivery>> findByCategory(Category category);



}
