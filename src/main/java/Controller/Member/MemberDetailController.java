package Controller.Member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;

import Command.Member.MemberCommand;
import Model.DTO.AuthInfo;
import Model.DTO.MemberDTO;
import Service.Member.MemberDetailService;
import Service.Member.MemberModifyService;
import Validator.MemberCommandValidator;

@Controller
public class MemberDetailController { //Validator이 필요없죠 그냥 클릭해서 가는거니까..

	@Autowired
	MemberDetailService memberDetailService;
	
	@Autowired
	MemberModifyService memberModifyService;
	
	
	@RequestMapping("/member/memberDetail")
	public String memberDetail(HttpSession session, Model model) { //항상 스트링.
		/*
		 * 내 정보는 어디있지? 현재 로그인 되어 있는 상태니까. 현재 어디있어?? session에 있지요~~
		 * 어디서 가져오면 되나요??? session에서 가져오면 되는거지 그럼 뭐가필요해?? session이 필요하겠죠
		 * 로그인정보를 저장하는 곳이 어디야? AuthInfo지 그럼 거기다가 세션값을 저장하면 되는거 아니야?
		 */
		
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo"); //오브젝트 타입에서 AuthInfo으로 변경
		
		
		//memberDetail.jsp의 c:if보다 먼저 여기가 인셉션이 걸렸다는데 이게 뭘가요? 그러면 memberDetailService 가보면 주석이 있어요!.	
		try {
			
			MemberDTO member = memberDetailService.memberDetail(authInfo);
			model.addAttribute("member", member);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "Member/memberDetail";
	}
	
	@RequestMapping("/member/memberModify")
	public String memberModify(HttpSession session, Model model) { 
		
		//공통 기능이라고 하며 AOP라는데 
		//Modify 와 Detail이랑 다를게 있어??? 없지 세션으로 찾아가지고 하는건데..
		//마치 게시판 할때처럼.. 수정에서 디테일 DAO를 이용한거 같으는낌
		
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo"); 

		try {
			
			MemberDTO member = memberDetailService.memberDetail(authInfo);
			model.addAttribute("memberCommand", member); //정보를 가져가는거고
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("memberCommand", new MemberDTO()); //업스면 객체생성인건가 결국에는
		}
		
		
		return "Member/memberModify";
	}
	
	@RequestMapping("/member/memberModifyPro") //디테일에서 수정을 하고나서 갈거야 그러면 model이 있을 필요가없고 저장을 할거잖아 그럼 필요한건 Command인데 어떤거 사용해?? memberCommand그대로 사용하면되지
	//memberModify.jsp로 부터 가져온 값을 memberCommand에 저장되어 있다. 
	public String memberModifyPro(HttpSession session, MemberCommand memberCommand, Errors errors) {
		
		//validate에서 pw와 pwCon을 비교해야 하는데 memberModify에서  pwCon을 받아오지 못했으니까(없으니까 거기에는) pw값을 pwCon에 저장
		memberCommand.setUserPwCon(memberCommand.getUserPw());
		
		new MemberCommandValidator().validate(memberCommand, errors);
		
		if(errors.hasErrors()) {
			return "Member/memberModify";
		}
		
		// 하지만 memberCommand에는 userId에 저장되어 있지가 않아 .. 그래서 session으로 부터 가져와야 한다.
		// ((AuthInfo)session.getAttribute("authInfo")) 얘가 AuthInfo래요.. 위에 형변환을 해서?
		memberCommand.setUserId(((AuthInfo)session.getAttribute("authInfo")).getId());

		// memberCommand가 가지고 잇는 값을 update하기 위해 인자로 넘긴다.
		Integer result = memberModifyService.memberModify(memberCommand);
		if(result > 0) {
			return "redirect:memberDetail";
		} else {
			errors.rejectValue("userPw", "pwbad");
			return "Member/memberModify";
		}
		
		
	}
}
