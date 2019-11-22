package Controller.Member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Command.Member.ChangePwdCommand;
import Service.Member.ChangePwService;
import Validator.ChangePwdCommandValidator;

@Controller
@RequestMapping("/edit/changePassword")
public class MemberPasswordController {  //get이랑 post랑 나누는 이유가 뭐야?
	
	@Autowired
	private ChangePwService changePwService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String memPw(@ModelAttribute("changePwdCommand") ChangePwdCommand pwCmd) {
		//원래 같으면 저 pw, Model model 적고 하단에 model.addttribute("command", new ChangePwdCommand()); << 이거랑 위에거랑 같은거입니다.
		return "Member/memberPw";
	}
	
	
	
	//ChangePwdCommand는 폼때문에 있어야한다.
	@RequestMapping(method = RequestMethod.POST)
	//session이 있어야 Id값을 가져올 수 있잖아. -> session을 통해서 id값을 가져오기 위해서!!! HttpSession객체 생성
	public String changePw(ChangePwdCommand changePwdCommand, Errors errors, HttpSession session) {
		//원래 같으면 저 pw, Model model 적고 하단에 model.addttribute("command", new ChangePwdCommand()); << 이거랑 위에거랑 같은거입니다.
		
		

		//ChangePwdCommandValidator에는 값이 있냐고 물어봤잖아 하지만 pw.jsp에서는 유저 패스워드 하나만 날라가잖아 하나만 날라가니까 ChangePwdCommandValidator에서는 당연이 없다고 하겠지 오류를 ...
		// 얘가 가지고 있는 값을 줘야지
		
		changePwdCommand.setNewPassword(changePwdCommand.getCurrentPassword()); //새로운 패스워드에다가 내가 적은 패스워드를 넣는다는데?
		changePwdCommand.setReNewPassword(changePwdCommand.getCurrentPassword());
		
		new ChangePwdCommandValidator().validate(changePwdCommand, errors);
		if(errors.hasErrors()) {
			return "Member/memberPw";
		}
		//id값을 changePwService에서 사용하기 위해서 session을 인자로 전달
		Boolean result = changePwService.currentPw(changePwdCommand, session);
		if(!result) { //false라면
			errors.rejectValue("currentPassword", "notCurrent"); //필드에 대한 에러코드
			return "Member/memberPw";
		}
		
		return "Member/memberPwModify";
	}
}
