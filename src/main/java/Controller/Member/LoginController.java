package Controller.Member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Command.Member.LoginCommand;
import Encrypt.Encrypt;
import Model.DTO.AuthInfo;
import Service.Member.AuthService;
import Validator.LoginCommandValidator;

@Controller
@RequestMapping("/login") //로그인이라는 주소가 날라왔을때만 사용
public class LoginController {
	@Autowired
	private AuthService authService;
	
	//post 방식 : 내가 계속해서 누르고 누르고 넘어가게 하는거
	// get방식 : 내가 위에다가 직접 쳐서 넘어가는거
	
	//근데 get방식으로 들어오면 안되잖아? 그걸 막기위해서 사용하는거
	@RequestMapping(method = RequestMethod.GET)									//remember라는 이름의 쿠키가 있다면 rememberCookie라는 쿠키를 만들어 줘라.. 
	public String form(Model model, LoginCommand loginCommand) {
		//그리고 main에 form이니까 커맨드 객체가 필요할거 아니야 ? 그니까 LoginCommand
		//그리고 커맨드를 날려야하니까 form에다가 커맨드 이름에다가 작성을 해줘야지
		//commandName이 command면 생략이 가능하다는거고 
		
		//쿠키가 널이 아니라면..
		

		//얘 프로젝트 바로 밑 결국 루트에 있는. /=>루트(컨텍스트 패스) redirect하면 커맨드 객체 안날라가니까 조심해야한다!
		return "Main/main"; 
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String submit(LoginCommand loginCommand, Errors errors, HttpSession session, HttpServletResponse response) { 
		// 세션을 디스패처로부터 받아올 수 있도록 되어 있으 스프링은.. 매개변수로 만들어 사용
		// 예전에는 HttpServletRequest request << 하지만 여기서는 requset를 안받잖아 
		// HttpSession session = request.getSession(); << jsp에서는 이런식으로 만들었었음.
		//response => 페이지 형태를 바꾸는거.., send쓰면 페이지 이동, 쿠키생성가능.
		
		
	
		//세션이 있어야 Authinfo에 저장을 할거아니야
		new LoginCommandValidator().validate(loginCommand, errors);
		if(errors.hasErrors()) { //필수 일때?? 입력안했을떄인가
			return "Main/main";
		}
		
		try { //null이면
			AuthInfo authInfo = authService.authenticate(loginCommand, response); //로그인 정보를 가져왔어.
			if(authInfo.getPw().equals(Encrypt.getEncryption(loginCommand.getPw()))) { //같은지 확인해야지 같으면
				session.setAttribute("authInfo",authInfo); // 그리고 가져온걸 session에 저장을 했어.

				Cookie autoLoginCookie = new Cookie("AutiLogin", loginCommand.getId1());
				autoLoginCookie.setMaxAge(60*60*24*30); 
				response.addCookie(autoLoginCookie);
				
			} else {
				System.out.println("비밀번호가 틀립니다.");
				errors.rejectValue("pw", "wrong");
			}
			return "Main/main";
		} catch(Exception e) {
			System.out.println("아이디가 존재하지 않습니다..");
			errors.rejectValue("id1", "notId");
			e.printStackTrace();
			return "Main/main";
		
		}
		
		
	}
	
	
}
