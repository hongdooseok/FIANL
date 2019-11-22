package Service.Member;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import Command.Member.MemberCommand;
import Encrypt.Encrypt;
import Model.DAO.MemberDAO;
import Model.DTO.MemberDTO;

@Service
public class MemberJoinService {
	
	
	private MemberDTO memberDTO = new MemberDTO(); //저걸 써도 setter안써도 된다는데? 어노테이션이면 안써도 된데요.

	@Autowired
	private MemberDAO memberDAO;
	
	public Integer execute(MemberCommand memberCommand, Model model) { //이러한 값을 커맨드가 받아오니까 커맨드를 넣어준거야
		Integer result = null;
		try {
		memberDTO.setUserId(memberCommand.getUserId());
		memberDTO.setUserPw(Encrypt.getEncryption(memberCommand.getUserPw())); //암호화 작성!
		memberDTO.setUserName(memberCommand.getUserName());
		
		SimpleDateFormat dt = new SimpleDateFormat("yyMMdd");
		Date date = dt.parse(memberCommand.getUserBirth());
		Timestamp userBirth = new Timestamp(date.getTime());
		memberDTO.setUserBirth(userBirth);
		
		memberDTO.setUserGender(memberCommand.getUserGender());
		memberDTO.setUserRmail(memberCommand.getUserRmail());
		memberDTO.setUserAddr(memberCommand.getUserAddr());
		memberDTO.setUserPh1(memberCommand.getUserPh1());
		memberDTO.setUserPh2(memberCommand.getUserPh2());
		
		result = memberDAO.inserMember(memberDTO);
		//멤버필드만 만들면 빈을 이용해서 사용하는거야!! 그래서 객체 생성을 할 필요가 없지 memberDAO memberDAO = new memberDAO();
		
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;	
	}
	
}
