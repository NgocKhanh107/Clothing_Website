package com.duan.entities;



import java.io.Serializable;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "User_Role")
public class UserRole implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="User_Id")
	Users user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="Role_Id")
	Role role;

	public UserRole(Users user, Role role) {
		this.user = user;
		this.role = role;
	}
}
