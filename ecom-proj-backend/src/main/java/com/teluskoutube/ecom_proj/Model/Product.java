package com.teluskoutube.ecom_proj.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;
     private String name;
     private String description;
     private String brand;
     private Long price;
     private String category;

//     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")

     private Date releaseDate;
     private boolean productAvailable;
     private int stockQuantity;

     private String imageName;
     private String imageType;
     @Lob //large object
    private byte[] imageDate;
}
