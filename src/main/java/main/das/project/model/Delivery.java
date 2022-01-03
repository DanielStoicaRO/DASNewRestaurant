package main.das.project.model;

import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Entity
@Table(name = "deliveries")
@Setter
@Getter
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    private  String food;
    private Integer price;
    private Category category;
    private String photo;
    private boolean isAvailable;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;



}
