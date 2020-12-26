package com.sprint2.personalworkout.entity;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "User Details")
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes = "Unique Id for User")
	private int id;
	@ApiModelProperty(notes = "Email of User")
	private String email;
	@ApiModelProperty(notes = "Password of user")
	private String password;
	@ApiModelProperty(notes = "User's Height")
	private int height;
	@ApiModelProperty(notes = "User's Weight")
	private int weight; 
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH  } )
	@JoinColumn(name="Ur_Id" , referencedColumnName="role_id")
	private Role role;

	public User() {
		super();
	}

	public User(User user) {
		super();
		this.email = user.email;
		this.password = user.password;
		this.height = user.height;
		this.weight = user.weight;
	}
	
	public User(String email, String password, int height, int weight, Role role) {
		super();
		this.email = email;
		this.password = password;
		this.height = height;
		this.weight = weight;
		this.role = role;
	}	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Role getRole() {
		return role;
	}

	public void setrole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", height=" + height + ", weight=" + weight	+ "]";
	}

}