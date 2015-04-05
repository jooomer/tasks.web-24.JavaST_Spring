package ua.store.annotation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneFieldValidator implements ConstraintValidator<PhoneField, String> {

	private Pattern pattern;
	private Matcher matcher;
	private static final String PHONE_PATTERN = "[0-9]*";
	
	@Override
	public void initialize(PhoneField arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isValid(String phone, ConstraintValidatorContext arg1) {
		pattern = Pattern.compile(PHONE_PATTERN);
		matcher = pattern.matcher(phone);
		return matcher.matches();
	}

}
