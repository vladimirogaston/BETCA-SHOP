package ar.ungs.shop.dtos;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import ar.ungs.shop.entities.UserEntity;
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

	@Size(max = 10, message = "Maximo 10 caracteres para el nombre de usuario")
	@NotBlank
	private String name;
	
	@Size(max = 10, message = "Maximo 10 caracteres para el password")
	@NotBlank
	private String password;
	
	@ListNotEmpty(message = "El usuario debe poseer un rol como mínimo")
	private List<Role> roles;
	
	@JsonInclude(value = Include.NON_NULL)
	private boolean active;

	public UserDto() {
		setId(null);
		setRoles(null);
		setPassword(null);
		setName(null);
		setActive(true);
	}

	public UserDto(UserEntity entity) {
		setId(entity.getId());
		setPassword(entity.getPassword());
		setName(entity.getName());
		setActive(entity.isActive());
		setRoles(entity.getRoles());
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
