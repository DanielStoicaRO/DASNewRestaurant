package main.das.project.dto;

import lombok.Getter;
import lombok.Setter;
import main.das.project.model.Category;

@Getter
@Setter
public class DeliveryInfo {

    private  String food;
    private Integer price;
    private Category category;
    private String photo;
    private boolean isAvailable;
}
