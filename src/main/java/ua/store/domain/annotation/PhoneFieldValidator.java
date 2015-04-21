package ua.store.domain.annotation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.store.domain.controller.user.RegisterController;

public class PhoneFieldValidator implements ConstraintValidator<PhoneField, String> {

	private static final Logger logger = LogManager.getLogger(PhoneFieldValidator.class);

	private Pattern pattern;
	private Matcher matcher;
	private static final String PHONE_PATTERN = "[0-9()-]*";
	
	@Override
	public void initialize(PhoneField arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isValid(String phone, ConstraintValidatorContext arg1) {
		logger.debug("--- started");
		pattern = Pattern.compile(PHONE_PATTERN);
		matcher = pattern.matcher(phone);
		boolean result = matcher.matches();
		logger.debug("Phone number: " + phone + "; Validation result: " + result);
		return result;
	}

}
