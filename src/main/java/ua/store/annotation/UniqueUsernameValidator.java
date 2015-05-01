package ua.store.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import ua.store.repository.UserRepository;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

	private static final Logger logger = LogManager.getLogger(UniqueUsernameValidator.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void initialize(UniqueUsername constraintAnnotation) {
	}

	@Override
	public boolean isValid(String userName, ConstraintValidatorContext context) {
		logger.debug("--- started");
		if (userRepository == null) {
			logger.debug("userRepository == null");
			return true;
		}
		boolean result = (userRepository.findByName(userName) == null);
		logger.debug("userName: " + userName + "; Validation result: " + result);
		return result;
	}

}
