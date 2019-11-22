package Service.Member;

import org.springframework.beans.factory.annotation.Autowired;

import Command.Member.MemberCommand;
import Encrypt.Encrypt;
import Model.DAO.MemberDAO;
import Model.DTO.MemberDTO;

public class MemberModifyService {

	@Autowired
	private MemberDAO memberDAO;
	
	public Integer memberModify( MemberCommand memberCommand) {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setUserPw(Encrypt.getEncryption(memberCommand.getUserPw()));
		memberDTO.setUserRmail(memberCommand.getUserRmail());
		memberDTO.setUserAddr(memberCommand.getUserAddr());
		memberDTO.setUserPh1(memberCommand.getUserPh1());
		memberDTO.setUserPh2(memberCommand.getUserPh2());
		memberDTO.setUserId(memberCommand.getUserId()); //아이디가 있어야 수정을 할거 아니야!!! 그래서 아이디 가져오기
		
		Integer result = memberDAO.memberModify(memberDTO);
		
		return result;
	}
}
