package Service.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Command.Member.LoginCommand;
import Model.DAO.MemberDAO;
import Model.DTO.AuthInfo;
import Model.DTO.MemberDTO;
@Service
public class MemberDetailService {
	
	@Autowired
	MemberDAO memberDAO;
	String aa;
	String vv;	
	String age;
	String sdsd;
	int aaaaaaaa;
	int bbbbbb;

	public MemberDTO memberDetail(AuthInfo authInfo) throws Exception {//memberDTO를 반환해달라고 해줬잖아.
		
		LoginCommand loginCommand = new LoginCommand();
		
		//memberDetail.jsp의 c:if보다 먼저 여기가 인셉션이 걸렸다는데 이게 뭘가요? 아 치고 들어가면 세션 값이 없으니까 여기서 null이 떠버리니까 인셉션이 걸린거죠 그러니까 throws Exception를 준거 ㅇㅋ..
		loginCommand.setId1(authInfo.getId()); //id 가져오기. 
		MemberDTO member = null;

		member = memberDAO.selectByUserId(loginCommand); //command에 아이디만 저장해서 보냈잖아. 
		//pw 비교 안하고 id만 비교하고 member 내용이 있잖아 그럼 굳이 새로 만들어야해?? 그냥 똑같은걸 이용하면 그만이잖아. (아이디만 찾고 인자값으로 아이디만 줬짢아)
		
		return member;
	}
	
}
