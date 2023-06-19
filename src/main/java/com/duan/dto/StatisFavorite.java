package com.duan.dto;


import com.duan.entities.Product;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class StatisFavorite {
    @Id
    private Serializable group;
    private Long count;
}
