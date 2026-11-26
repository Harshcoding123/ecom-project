package com.example.ecom_project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //this will auto generate the ID we don't hae to manually write
    private int id;
    private String name;
    private String desc;
    private String brand;
    private long price;
    private String category;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="dd-MM-yyyy")
    //used to correct the format of date
    private Date release_date;
    private boolean available;
    private int quantity;
    private String imageName;
    private String imageType;
    @Lob
    private byte[] imageData;  //image to array of bytes


}
