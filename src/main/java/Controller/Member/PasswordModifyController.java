package Controller.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Command.Member.ChangePwdCommand;
import Service.Member.PasswordModifyService;
import Validator.ChangePwdCommandValidator;

@Controller
public class PasswordModifyController {

	
	@Autowired
	PasswordModifyService passwordModifyService;
	
	@RequestMapping(value="/edit/pwModifyPro", method = RequestMethod.POST)
	public String pwModify(ChangePwdCommand changePwdCommand, Errors errors, HttpSession session, HttpServletRequest request) { //커맨드는 뭐하고 같아야해?? 뷰하고 같아야해 값을 보여주니까.
		
		new ChangePwdCommandValidator().validate(changePwdCommand, errors);
		
		if(errors.hasErrors()) {
			return "Member/memberPwModify";
		}
		
		Integer result = passwordModifyService.updatePassword(changePwdCommand, session);
		
		if(result > 0) {
			return "redirect:"+ request.getContextPath() +"/main"; //이거나 아래거나 같은거다!! ..은 내가 정확한 주소를 알았을때지만 정확하게 몰랐을때는 ..은 위험한거니 이렇게 하자!!
		} else {
			errors.rejectValue("currentPassword", "notCurrent");
			return "redirect:../main";
		}
		
		
	}
}
