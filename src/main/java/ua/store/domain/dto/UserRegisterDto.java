package ua.store.domain.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import ua.store.domain.annotation.ConfirmPassword;
import ua.store.domain.annotation.PhoneField;
import ua.store.domain.annotation.UniqueUsername;

@ConfirmPassword(message = "Passwords do not match.")
public class UserRegisterDto {

	@Size(min = 3, max = 20, message = "Name must be at least 3 and no more 20 characters!")
	@UniqueUsername(message = "Such username already exists!")
	private String name;

	@Size(max = 20, message = "First name must be no more 20 characters!")
	private String firstName;

	@Size(max = 20, message = "Last name must be no more 20 characters!")
	private String lastName;

	@Email(message = "Invalid email address!")
	@Size(min = 1, message = "Invalid email address!")
	private String email;

	@PhoneField(message = "Phone must contain just numbers!")
	@Size(max = 20, message = "Phone can be no more 20 characters!")
	private String phone;

	@Size(max = 50, message = "Address must be no more 50 characters!")
	private String address;
	
	@Size(min = 3, max = 100, message = "Password must be at least 3 and no more 100 characters!")
	private String password;
	
	private String confirmPassword;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	

}
