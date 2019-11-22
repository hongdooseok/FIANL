package Service.Member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import Command.Member.ListCommand;
import Model.DAO.MemberDAO;
import Model.DTO.MemberDTO;

@Service
public class MemberListService {
	
	@Autowired
	private MemberDAO membarDAO;
	
	public void memberList(Model model, ListCommand listCommand) {
		List<MemberDTO> members = membarDAO.selectList(listCommand);
		if(members != null) {
			model.addAttribute("members", members);
		}
	}

}
