package Validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import Command.AnswerBoard.BoardReplyCommand;

public class AnswerBoardValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return BoardReplyCommand.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
		ValidationUtils.rejectIfEmpty(errors, "AnswerBoardCommand.boardName", "required");
		ValidationUtils.rejectIfEmpty(errors, "AnswerBoardCommand.boardSubject", "required");
		ValidationUtils.rejectIfEmpty(errors, "AnswerBoardCommand.boardContent", "required");
		ValidationUtils.rejectIfEmpty(errors, "AnswerBoardCommand.boardPass", "required");
		
		
	}

}
