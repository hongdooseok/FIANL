package Service.Member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Command.Member.ChangePwdCommand;
import Encrypt.Encrypt;
import Model.DAO.MemberDAO;
import Model.DTO.AuthInfo;

@Service
public class PasswordModifyService {
	@Autowired
	private MemberDAO memberDAO;
	
	public Integer updatePassword(ChangePwdCommand changePwdCommand, HttpSession session) {
		
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		String userId = authInfo.getId();
		
		Integer result = memberDAO.pwUpdate(userId, Encrypt.getEncryption(changePwdCommand.getCurrentPassword()), Encrypt.getEncryption(changePwdCommand.getNewPassword()));
		
		return result;
	}

}
