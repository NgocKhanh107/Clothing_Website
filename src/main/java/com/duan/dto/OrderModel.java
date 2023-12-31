package com.duan.dto;

import java.io.Serializable;


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
public class OrderModel {
	@Id
	private String name;
	private String date;
	private Long price;
	private boolean status;
}
