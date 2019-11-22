package Controller.Member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {
	
	@RequestMapping("/logout")  // 로그아웃일때만 사용!!
	public String logout(HttpSession session, HttpServletResponse response) {
		session.invalidate(); //세션을 삭제합니다.
		
		Cookie autoLoginCookie = new Cookie("AutoLogin", "1111");
		autoLoginCookie.setMaxAge(0);
		response.addCookie(autoLoginCookie);
		
		
		return "redirect:/main";
	}
	
	
	
	/*
	 * //로그인하고 로그아웃의 차이는 ??
	 * 로그인은 위에다 놨어 근데 로그아웃은 못찾았어 그 차이는?
	 * 뭐냐면 get post로 나눠져 있잖아. 함수를 여러개 사용할 경우 위에다 놓고 사용이 가능하지만
	 * 로그아웃 처럼 get post가 다 한가지 방법으로만 사용하겠다고 했으니 위에다 두면 못찾을 수도 있으니 
	 * 밑에다 두는것이 좋다!!
	 */
	
}
