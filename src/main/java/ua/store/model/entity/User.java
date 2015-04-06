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
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ua.store.annotation.PhoneField;
import ua.store.annotation.UniqueUsername;
import ua.store.annotation.ConfirmPassword;

@Entity
@Table(name = "user")
//@ConfirmPassword(message = "Passwords do not match.")
public class User implements Comparable<User> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

//	@Size(min = 3, max = 20, message = "Name must be at least 3 and no more 20 characters!")
//	@UniqueUsername(message = "Such username already exists!")
	@Column(length = 100, unique = true, nullable = false)
	private String name;

//	@Size(min = 3, max = 100, message = "Password must be at least 3 and no more 100 characters!")
	@Column(length = 100, nullable = false)
	private String password;
	
//	@Transient
//	private String confirmPassword;
//	
	private boolean enabled;

//	@Size(max = 20, message = "First name must be no more 20 characters!")
	@Column(name = "first_name", length = 100)
	private String firstName;

//	@Size(max = 20, message = "Last name must be no more 20 characters!")
	@Column(name = "last_name", length = 100)
	private String lastName;

//	@Email(message = "Invalid email address!")
	@Size(min = 1, message = "Invalid email address!")
	@Column(length = 100, nullable = false)
	private String email;

//	@PhoneField(message = "Phone must contain just numbers!")
//	@Size(max = 20, message = "Phone can be no more 20 characters!")
	@Column(length = 20)
	private String phone;

//	@Size(max = 50, message = "Address must be no more 50 characters!")
	@Column(length = 100)
	private String address;
	
//	@Size(max = 100, message = "Comments must be no more 100 characters!")
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
		userType = role.getName().toString();
	}
	
	@Override
	public String toString() {
		return "\n"
				+ "User ------------------------------- \n" 
				+ "Id:              " + id + "\n" 
				+ "name:            " + name + "\n"
				+ "firstName:       " + firstName + "\n"
				+ "lastName:        " + lastName + "\n"
				+ "Email:           " + email + "\n"
				+ "Phone:           " + phone + "\n"
				+ "Address:         " + address + "\n"
				+ "Password:        " + password + "\n"
//				+ "ConfirmPassword: " + confirmPassword + "\n"
				+ "roles:           " + userType + "\n";
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

/*	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
*/
}
