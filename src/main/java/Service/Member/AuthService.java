package Service.Member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Command.Member.LoginCommand;
import Model.DAO.MemberDAO;
import Model.DTO.AuthInfo;
import Model.DTO.MemberDTO;
@Service
public class AuthService {
	
	@Autowired
	private MemberDAO memberDao;

	
	public AuthInfo authenticate(LoginCommand loginCommand, HttpServletResponse response)  throws Exception{
		
		// 쿠키생성.
		Cookie rememberCookie = new Cookie("remember", loginCommand.getId1()); //쿠키에다가 아이디 저장.
		rememberCookie.setPath("/"); // 현재 주소는 루트니까..
		if(loginCommand.getIdStore() == true) {
			
			//쿠키 생명주기
			rememberCookie.setMaxAge(60*60*24*30); //60초 * 60초 * 24시간 * 30일. => 그러면 한달.?
			
		} else {
			//쿠키 생명주기
			rememberCookie.setMaxAge(0); //쿠키 생성이 안되면 삭제로 0을 준다.
		}
		//웹 브라우저에 보내야 하잖아 그때 사용하는것이 response잖아.
		response.addCookie(rememberCookie);
		
		MemberDTO member = memberDao.selectByUserId(loginCommand);
		AuthInfo authInfo = new AuthInfo(member.getUserId(), member.getUserRmail(), member.getUserName(), member.getUserPw()); //vlaue값을 넣어줘야해서 의존객체 안돠ㅣㅁ
		//생성자에 반환을 해줬어요!
		//Validator을 사용하기 위해서 여기에서 아무것도 작업을 안한거 하지만 이것을 사용안하려면 여기에서 작업을 해줘야함(결국 세션 이런건가?)
		
		return authInfo;
	}
	
	
}
