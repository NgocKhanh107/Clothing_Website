package com.duan.dto;


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

public class AddressModel {
	private String firstname;
	private String lastname;
	private String email;
	private String phone;
	private String provincial;
	private String address;
	private boolean payment = true;

	
}
