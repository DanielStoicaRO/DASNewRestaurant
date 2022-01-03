package main.das.project.dto;

import lombok.Getter;
import lombok.Setter;
import main.das.project.model.Category;

import javax.persistence.Transient;

@Getter
@Setter
public class DeliveryDto {

    private  Integer id;
    private  String food;
    private Integer price;
    private String category;
    private String photo;
    private boolean isAvailable;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Transient
    public String getPhotoImagePath() {
        if (photo == null || id == null) return null;

        return "/resources/static/images2/" + id + "/" + photo;
    }

}
