package Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import Command.Member.MemberCommand;

public class MemberCommandValidator implements Validator {
	
	//이메일 검사하려는 소스를 만드려고 해요!!
	private static final String emailRegExp =
			"^[_A-Za-z0-9-]+(.[_A-Za-z0-9]+)*@(?:\\w+\\.)+\\w+$"; //정규식이요!
	//^[_A-Za-z0-9-]+ 첫글자 언더바 또는 대문자A ~Z a~z 0~9까지 + 뒤에는 모든글자(어떤글자 몇백자가 와도 상관없다)
	//(.[_A-Za-z0-9]+)는 묶는다고 . 다음에 _바 또는 A~Z a~z 0~9까지 + 뒤에는 모든글자
	// @ 
	//@다음에 \\w+=>문자만 올 수 있다. 특수문자도 가능.(아스키 코드를 의미)
	//끝문자는 워드로 끝나야 하는데 어떤글자도 상관없음
	
	
	private Pattern pattern; //패턴이라는 객체
	
	public MemberCommandValidator() { // 이 함수를 실행할 때 패턴객체가 저 값을 이용할 수 있게 하는거.. (컴파일이라구)
		pattern = Pattern.compile(emailRegExp);
	}
	
	

	public boolean supports(Class<?> clazz) {
		
		return MemberCommand.class.isAssignableFrom(clazz);
	}
	
	// validate라는걸 실행시키면  supports라는 메소드를 자동으로 실행시켜주는거임.(그렇게 오버라이드 되어있음)
	// 
	// Object target이 Class<?> clazz 인자값으로 들어가
	/*
	 * 내가 멤버ㅓ맨드인지 확인을 하고 확인을 하려면 sup를 실행해요 class<ta>들어가 그럼 clazz가 멤버클래스인지 확인을 하는거야.
	 * 에러가 없으면 형변환을 시켜주잖아.
	 * if로 내가 이메일인지 물어보는거고 ex    sss@naa     이런 공백을 없애는게 (양옆) -> trim
	 * get내가 입력받은 이메일에 양옆 공백을 없애고 비어있는지 물어보는거. 
	 * 공백이면 에러메시지 보내는거임.. 이메일이라는 항목에다가  required라는 글자가 출력되게 하려고 저기작성 저거는 label.pro 여기에다가 적을거임 나중에
	 * path가 email이여야 하는거야 formform 의 path 이거<<<< 
	 * 
	 * 
	 * 
	 * 패턴이 이메일과 일치하냐고 묻는거임 (매치) -> 패턴은 이미 항목이 만들어져 안에 있잖아 내가 입력한거랑 비교해서 맞으면 true 아니면 false값을 가질거야 
	 *  if문은 비교를 하는거지 false면 에러를 표시해라 이메일에 항목을 또 띄워주자!! 
	 *  
	 *  
	 *  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required"); < 공백문자 이거나, 비어있거나 이름이라는 항목에 에러를 출력하는거 !! 
	 *  
	 *  커맨드에 있는 멤버필드랑 같아야지 !! 커맨드는 누구랑 같아?? form이랑 같지
	 * 
	 */
	public void validate(Object target, Errors errors) { 
		MemberCommand regReq = (MemberCommand) target; //targer은 멤버 커맨드냐고 묻는거임 형변환 커맨드로 
		if (regReq.getUserRmail() == null || regReq.getUserRmail().trim().isEmpty()) {
			/*
			 * Errors의 rejectValue()메서드는 
			 * 첫 번째 파라미터로 프로퍼터의 이름을 전달받고, 두 번째 파라미터로 프로퍼티의 에러 코드를 설정한다.
			 *  		즉 멤버필드(커맨드)
			 */
			errors.rejectValue("userRmail", "required");
		} else {
			Matcher matcher = pattern.matcher(regReq.getUserRmail());	
			if(!matcher.matches()) {
				//"userEmail" 프로퍼티의 에러 코드로 "bad"를 추가
				errors.rejectValue("userRmail", "bad"); // 이거랑 ValidationUtils랑 같다는 뜻인데?
			}
		}
		//ValidationUtils z클래스는 객체의 값 검증코드를 간결하게 작성할 수 있도록 도와준다.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "required"); //errors라는 인자값.
		ValidationUtils.rejectIfEmpty(errors, "userPw", "required");
		ValidationUtils.rejectIfEmpty(errors, "userPwCon", "required");
		ValidationUtils.rejectIfEmpty(errors, "userId", "required");
		ValidationUtils.rejectIfEmpty(errors, "userPh1", "required");
		ValidationUtils.rejectIfEmpty(errors, "userAddr", "required");
		ValidationUtils.rejectIfEmpty(errors, "userBirth", "required");
		
		
		if(!regReq.getUserPw().isEmpty()) { //공백이 아니면 
			if(!regReq.isPasswordEqualToConfirmPassword()) { //같냐고 물어볼거야 false면 에러 메시지를 주려고 하는거야.
				errors.rejectValue("userPwCon", "nomatch");
			}
		}
	}

}
