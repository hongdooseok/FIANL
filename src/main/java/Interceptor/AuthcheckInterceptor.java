package Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// 차단하기.
public class AuthcheckInterceptor extends HandlerInterceptorAdapter { 

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// session이 없을 때만 Interceptor
		HttpSession session = request.getSession();
		if(session != null) {
			Object authInfo = session.getAttribute("authInfo");
			if(authInfo == null) {
				return true;
			}
			
		} //정상적인?
		
		//결과적으로는 세션을 생성을해서 if문으로 비교를하지 세션의 값이 null이 아니냐  그러면  
		
		
		response.sendRedirect(request.getContextPath() + "/main");
		
		return false;
	}
	
}
