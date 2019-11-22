package Controller.Member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Command.Member.LoginCommand;
import Service.Member.MainService;

@Controller
@RequestMapping("/main") //로그인이라는 주소가 날라왔을때만 사용
public class MainController {
	@Autowired
	private MainService mainService;
	
	@RequestMapping(method = RequestMethod.GET)									//remember라는 이름의 쿠키가 있다면 rememberCookie라는 쿠키를 만들어 줘라.. 
	public String form(Model model, LoginCommand loginCommand, @CookieValue(value="remember", required=false) Cookie rememberCookie, @CookieValue(value="AutiLogin", required=false) Cookie autoLoginCookie, HttpSession session) {
		//그리고 main에 form이니까 커맨드 객체가 필요할거 아니야 ? 그니까 LoginCommand
		//그리고 커맨드를 날려야하니까 form에다가 커맨드 이름에다가 작성을 해줘야지
		//commandName이 command면 생략이 가능하다는거고 
		
		//쿠키가 널이 아니라면..
		if(rememberCookie != null) {
			loginCommand.setId1(rememberCookie.getValue());
			loginCommand.setIdStore(true);
		}
		
		if(autoLoginCookie != null) {
			loginCommand.setId1(autoLoginCookie.getValue());
			
			mainService.autoLogin(session, loginCommand);
		}
		

		//얘 프로젝트 바로 밑 결국 루트에 있는. /=>루트(컨텍스트 패스) redirect하면 커맨드 객체 안날라가니까 조심해야한다!
		return "Main/main"; 
	}
}
