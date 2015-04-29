package ua.store.domain.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.store.domain.entity.Category;

public class CategoryRequiredValidator implements ConstraintValidator<CategoryRequired, Category> {

	private static final Logger logger = LogManager.getLogger(CategoryRequiredValidator.class);
	
	@Override
	public void initialize(CategoryRequired arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isValid(Category category, ConstraintValidatorContext arg1) {
		if (category.getName() == null
				|| category.getName().equals("")) {
			logger.debug("Error. Category isn't valid.");
			return false;
		}
		logger.debug("Category is valid.");
		return true;
	}



}
