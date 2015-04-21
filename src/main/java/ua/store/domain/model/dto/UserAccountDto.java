package ua.store.domain.model.dto;

import javax.persistence.Column;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import ua.store.domain.annotation.PhoneField;

public class UserAccountDto {

	@Size(max = 20, message = "First name must be no more 20 characters!")
//	@Column(name = "first_name", length = 100)
	private String firstName;

	@Size(max = 20, message = "Last name must be no more 20 characters!")
//	@Column(name = "last_name", length = 100)
	private String lastName;

	@Email(message = "Invalid email address!")
	@Size(min = 1, message = "Invalid email address!")
//	@Column(length = 100, nullable = false)
	private String email;

	@PhoneField(message = "Phone must contain just numbers!")
	@Size(max = 20, message = "Phone can be no more 20 characters!")
//	@Column(length = 20)
	private String phone;

	@Size(max = 50, message = "Address must be no more 50 characters!")
//	@Column(length = 100)
	private String address;
	
	@Size(max = 50, message = "Address must be no more 1000 characters!")
//	@Column(length = 1000)
	private String comments;
	
	private boolean inBlackList;

	private boolean enabled;
	
	@Override
	public String toString() {
		return "\n"
				+ "UserAccountDto ------------------------------- \n" 
				+ "firstName:       " + firstName + "\n"
				+ "lastName:        " + lastName + "\n"
				+ "Email:           " + email + "\n"
				+ "Phone:           " + phone + "\n"
				+ "Address:         " + address + "\n";
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	

}
