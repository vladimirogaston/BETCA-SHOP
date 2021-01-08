package ar.ungs.shop.dtos;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ar.ungs.shop.dtos.validations.ListNotEmpty;
import ar.ungs.shop.entities.Role;

public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonInclude(value = Include.NON_NULL)
	private Integer id;
	
	@Max(value = 20)
	@NotBlank
	private String name;
	
	@Max(value = 20)
	@NotBlank
	private String password;
	
	@ListNotEmpty
	private List<Role> roles;
	
	@JsonInclude(value = Include.NON_NULL)
	private boolean active;

	public UserDto() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
