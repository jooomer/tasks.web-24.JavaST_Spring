package ua.store.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ua.store.model.entity.User;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, User> {

	private static final Logger logger = LogManager.getLogger(ConfirmPasswordValidator.class);
	
	@Override
	public void initialize(ConfirmPassword arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isValid(User user, ConstraintValidatorContext arg1) {
		logger.debug("--- started");
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		String confirmPassword = encoder.encode(user.getConfirmPassword());
//		String confirmPassword = user.getConfirmPassword();
		boolean result = user.getPassword().equals(user.getConfirmPassword());
		logger.debug("Password: " + user.getPassword() 
				+ "; ConfirmPassword: " + user.getConfirmPassword() 
				+ "; Validation result: " + result);
		return result;
	}


}
