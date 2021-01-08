package ar.ungs.shop.entities;

public enum Role {
	
	OPERATOR, ADMIN, CUSTOMER, AUTHENTICATED;

	public String roleName() {
		return "ROLE_" + this.toString();
	}

	public boolean isRole() {
		for(Role role: Role.values()) {
			if(!role.roleName().equals(this)) {
				return false;
			}
		}
		return true;
	}
}
