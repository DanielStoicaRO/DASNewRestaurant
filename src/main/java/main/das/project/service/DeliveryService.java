package main.das.project.service;

import main.das.project.controller.exception.ResourceNotFoundException;
import main.das.project.dto.DeliveryDto;
import main.das.project.dto.DeliveryInfo;
import main.das.project.mapper.DeliveryMapper;
import main.das.project.model.Category;
import main.das.project.model.Delivery;
import main.das.project.repository.DeliveryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryService {

    private static final Logger log = LoggerFactory.getLogger(DeliveryService.class);

    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;
    private final UserService userService;

    @Autowired
    public DeliveryService(DeliveryRepository deliveryRepository,
                           DeliveryMapper deliveryMapper,
                           UserService userService) {
        this.deliveryRepository = deliveryRepository;
        this.deliveryMapper = deliveryMapper;
        this.userService = userService;
    }


    public List<DeliveryInfo> findByCategory(Category category) {
        log.info("find delivery by category {}", category);

        return deliveryRepository.findByCategory(category)
                .map(deliveryList -> deliveryMapper.mapDeliveryToDto(deliveryList))
                .orElseThrow(() -> new ResourceNotFoundException("delivery not found"));
    }

    public List<DeliveryDto> findAll() {
        return deliveryRepository.findAll().stream()
                .map(delivery -> deliveryMapper.mapToDelivery(delivery))
                .collect(Collectors.toList());
    }

    public DeliveryDto save(DeliveryDto deliveryDto) {

        deliveryDto.setAvailable(true);
        Delivery delivery = deliveryMapper.mapToDeliveryDto(deliveryDto);
        Delivery savedDelivery = deliveryRepository.save(delivery);
        DeliveryDto savedDto = deliveryMapper.mapToDelivery(savedDelivery);
        return savedDto;
    }

    public DeliveryDto findById(Integer id) {
        log.info("finding delivery with id {}", id);
        // find by id from repo
        return deliveryRepository.findById(id)
                // convert to dto
                .map(delivery -> deliveryMapper.mapToDelivery(delivery))
                .orElseThrow(() -> new ResourceNotFoundException("delivery not found"));
    }

    public void update(DeliveryDto deliveryDto) {
        log.info("updating delivery with id {} with data {}", deliveryDto.getId(), deliveryDto);
        // find entity by id
        deliveryRepository.findById(deliveryDto.getId())
                .map(delivery -> deliveryMapper.update(delivery, deliveryDto))
                .map(updatedDelivery -> deliveryRepository.save(updatedDelivery))
                .orElseThrow(() -> new ResourceNotFoundException("pet not found"));
    }

    public void deleteById(Integer id) {
        deliveryRepository.deleteById(id);
    }

}
