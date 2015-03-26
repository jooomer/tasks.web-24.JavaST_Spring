package ua.store.model.entity;

import java.util.ArrayList;
import java.util.List;

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
	@Column(nullable = false)
	private Integer id;

	@Size(min = 3, message = "Name must be at least 3 characters!")
	@Column(length = 100, unique = true, nullable = false)
	@UniqueUsername(message = "Such username already exists!")
	private String name;

	@Size(min = 4, message = "Name must be at least 4 characters!")
	@Column(length = 100, nullable = false)
	private String password;

	private boolean enabled;

	@Column(name = "first_name", length = 100)
	private String firstName;

	@Column(name = "last_name", length = 100)
	private String lastName;

	@Email
	@Size(min = 1, message = "Invalid email address!")
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
	private List<Role> roles = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Product> products = new ArrayList<>();

	public void addRole(Role role) {
		roles.add(role);
		userType = role.getName();
	}

	public void addProduct(Product product) {
		products.add(product);
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
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

}
