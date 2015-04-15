package ua.store.model.entity;

public enum RoleType {
	ROLE_USER("User"), 
	ROLE_ADMIN("Admin"), 
	ROLE_MANAGER("Manager");
	
	private String name;
	
	RoleType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
