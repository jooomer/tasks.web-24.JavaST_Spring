package ua.store.domain.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.store.domain.dto.UserRegisterDto;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, UserRegisterDto> {

	private static final Logger logger = LogManager.getLogger(ConfirmPasswordValidator.class);
	
	@Override
	public void initialize(ConfirmPassword arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isValid(UserRegisterDto userRegisterDto, ConstraintValidatorContext arg1) {
		logger.debug("--- started");
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		String confirmPassword = encoder.encode(user.getConfirmPassword());
//		String confirmPassword = user.getConfirmPassword();
		boolean result = userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword());
		logger.debug("Password: " + userRegisterDto.getPassword() 
				+ "; ConfirmPassword: " + userRegisterDto.getConfirmPassword() 
				+ "; Validation result: " + result);
		return result;
	}


}
