package Service.Member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Command.Member.ChangePwdCommand;
import Command.Member.LoginCommand;
import Encrypt.Encrypt;
import Model.DAO.MemberDAO;
import Model.DTO.AuthInfo;
import Model.DTO.MemberDTO;

@Service
public class ChangePwService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	
	
	public Boolean currentPw(ChangePwdCommand changePwdCommand, HttpSession session) {
		
		LoginCommand loginCommand = new LoginCommand();
		//왜 DTO를 보내? session하려고? 
		
		loginCommand.setId1(((AuthInfo)session.getAttribute("authInfo")).getId());
	

		// currentPw값을 암호화 시
		
		MemberDTO member = memberDAO.selectByUserId(loginCommand);
		String pw = Encrypt.getEncryption(changePwdCommand.getCurrentPassword());
		//암호화 된 값을 비교
		if(member.getUserPw().equals(pw)) {
			return true;
		}
		
		return false;
		
	}

}
