package main.das.project.mapper;


import main.das.project.dto.DeliveryDto;
import main.das.project.dto.DeliveryInfo;
import main.das.project.model.Category;
import main.das.project.model.Delivery;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DeliveryMapper {

    public List<DeliveryInfo> mapDeliveryToDto(List<Delivery> entities) {
        return entities.stream()
                .map(delivery -> mapFromDeliveryToDeliveryInfo(delivery))
                .collect(Collectors.toList());
    }

    public DeliveryInfo mapFromDeliveryToDeliveryInfo(Delivery delivery) {
        DeliveryInfo deliveryInfo = new DeliveryInfo();
        deliveryInfo.setFood(delivery.getFood());
        deliveryInfo.setCategory(delivery.getCategory());
        deliveryInfo.setPrice(delivery.getPrice());
        deliveryInfo.setAvailable(delivery.isAvailable());
        deliveryInfo.setPhoto(delivery.getPhoto());
        return deliveryInfo;
    }

    public DeliveryDto mapToDelivery(Delivery delivery) {
        DeliveryDto dto = new DeliveryDto();
        dto.setId(delivery.getId());
        dto.setFood(delivery.getFood());
        dto.setCategory(String.valueOf(delivery.getCategory()));
        dto.setPrice(delivery.getPrice());
        dto.setAvailable(delivery.isAvailable());
        dto.setPhoto(delivery.getPhoto());
        return dto;
    }

    public Delivery mapToDeliveryDto(DeliveryDto dto) {
        Delivery entity = new Delivery();
        entity.setFood(dto.getFood());
        entity.setCategory(Category.valueOf(dto.getCategory()));
        entity.setPrice(dto.getPrice());
        entity.setAvailable(dto.isAvailable());
        entity.setPhoto(dto.getPhoto());
        return entity;
    }

    // update(DeliveryDto dto, Delivery entity)
    public Delivery update(Delivery deliveryToUpdate, DeliveryDto deliveryDto) {
        deliveryToUpdate.setFood(deliveryDto.getFood());
        deliveryToUpdate.setCategory(Category.valueOf(deliveryDto.getCategory()));
        deliveryToUpdate.setPrice(deliveryDto.getPrice());
        deliveryToUpdate.setAvailable(deliveryDto.isAvailable());
        deliveryToUpdate.setPhoto(deliveryDto.getPhoto());
        return deliveryToUpdate;
    }

}
