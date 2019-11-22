package Controller.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;

import Command.Member.ListCommand;
import Service.Member.MemberListService;

@Controller //뭐할때 불러올까? 메인에서 회원정보를
public class MemberListController {
	
	@Autowired
	MemberListService memberListService; 
	
	@RequestMapping("/member/List")
	public String List(Model model, ListCommand listCommand, Errors errors) {
		
		if(errors.hasErrors()) {
			return "Member/memberList"; //커맨드 형식에 일치 하지 않다는거.
		}
		
		if(listCommand.getFrom() != null && listCommand.getTo() != null) { //!!
			//페이지 정보를 가져와야지?? 그리고 보여주려면 model이 필요하잖아.
			memberListService.memberList(model, listCommand);
		}
		
	
		
		return "Member/memberList";
	}

}
