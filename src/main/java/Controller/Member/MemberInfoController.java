package Controller.Member;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Model.DTO.AuthInfo;
import Model.DTO.MemberDTO;
import Service.Member.MemberDetailService;

@Controller
public class MemberInfoController {
	
	@Autowired
	private MemberDetailService memberDetailService;

	@RequestMapping("/member/memberInfo/{id}") //{id}이거는 어떠한 글자도 상관 X 대신 하단에 이렇게 작성해줘야함 PathParam주고 value값에 적기!
	public String memberInfo(@PathVariable(value = "id") String userId, Model model) {
		
		AuthInfo authInfo = new AuthInfo();
		
		authInfo.setId(userId);
		
		try {
			MemberDTO memberDTO = memberDetailService.memberDetail(authInfo);
			model.addAttribute("member", memberDTO); //디테일.jsp에서 member로 받아서 여기도 member로 한다는데
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Member/memberInfo";
	}
}
