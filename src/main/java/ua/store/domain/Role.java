package ua.store.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Integer id;
	
	@Column(length = 100)
	@Enumerated(EnumType.STRING)
	private Name name;
	
	@ManyToMany(mappedBy = "roles")
	private List<User> users = new ArrayList<>();
	
	public static enum Name {
		ROLE_USER("User"), 
		ROLE_ADMIN("Admin"), 
		ROLE_MANAGER("Manager");
		
		private String name;
		
		Name(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
	}
	
	public void addUser(User user) {
		users.add(user);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
