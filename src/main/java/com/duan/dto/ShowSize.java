package com.duan.dto;

import com.duan.entities.Product;
import com.duan.entities.Size;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class ShowSize {

    @Id
    Serializable group;
    Long count;
}
