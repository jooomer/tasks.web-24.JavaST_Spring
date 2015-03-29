package ua.store.model.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ua.store.annotation.UniqueUsername;

@Entity
@Table(name = "user")
public class User implements Comparable<User> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

//	@Size(min = 3, message = "Name must be at least 3 characters!")
	@Column(length = 100, unique = true, nullable = false)
	@UniqueUsername(message = "Such username already exists!")
	private String name;

//	@Size(min = 4, message = "Name must be at least 4 characters!")
	@Column(length = 100, nullable = false)
	private String password;

	private boolean enabled;

	@Column(name = "first_name", length = 100)
	private String firstName;

	@Column(name = "last_name", length = 100)
	private String lastName;

	@Email
//	@Size(min = 1, message = "Invalid email address!")
	@Column(length = 100)
	private String email;

	@Column(length = 20)
	private String phone;

	@Column(length = 100)
	private String address;
	
	@Column(length = 1000)
	private String comments;
	
	@Column(name = "in_black_list")
	private boolean inBlackList;
	
	private String userType;

	@ManyToMany
	@JoinTable
	private Set<Role> roles = new HashSet<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Order> orders = new HashSet<>();
	
	public void addRole(Role role) {
		roles.add(role);
		userType = role.getName();
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public boolean isInBlackList() {
		return inBlackList;
	}

	public void setInBlackList(boolean inBlackList) {
		this.inBlackList = inBlackList;
	}

	@Override
	public int compareTo(User user) {
		return this.id - user.getId();
	}
	
	@Override
	public String toString() {
		return "User - Id: " + id + "; name: " + name + "; roles: "; 
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

}
