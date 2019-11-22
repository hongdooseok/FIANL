package Service.Member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Command.Member.LoginCommand;
import Model.DAO.MemberDAO;
import Model.DTO.AuthInfo;
import Model.DTO.MemberDTO;

@Service
public class MainService {
	@Autowired
	private MemberDAO memberDao;
	
	public void autoLogin(HttpSession session, LoginCommand loginCommand) {
		MemberDTO member = memberDao.selectByUserId(loginCommand);
		
		AuthInfo authInfo = new AuthInfo(member.getUserId(), member.getUserRmail(), member.getUserName(), member.getUserPw());
		session.setAttribute("authInfo", authInfo);
	}

}
