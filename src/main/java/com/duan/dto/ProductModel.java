package com.duan.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ProductModel {
    private int id;
    private String name;
    private int price = 0;
    private MultipartFile image;
    private String origin;
    private String material;
    private String describe;
    private String review;
    private String manufacture;
    private String manuDay;
}
