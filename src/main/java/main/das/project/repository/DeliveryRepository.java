package main.das.project.repository;

import main.das.project.model.Category;
import main.das.project.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {

    Optional<Delivery> findByFood(String food);

    Optional<List<Delivery>> findByCategory(Category category);

}
