package com.duan.dto;



import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class FillProSize {
	@Id
	private Integer id;
	private String nameProduct;
	private String nameSize;
}
