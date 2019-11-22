package Validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import Command.Member.LoginCommand;

public class LoginCommandValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return LoginCommand.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id1", "required"); //공백문자까지 채우는거구
		ValidationUtils.rejectIfEmpty(errors, "pw", "required"); //그냥 값이 없나 있나
	}
	
	

}
