package com.duan.dto;





import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class FillProColor {
	@Id
	private Integer id;
	private String image;
	private String nameProduct;
	private String nameColor;
}
