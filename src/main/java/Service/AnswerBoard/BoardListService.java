package Service.AnswerBoard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import Model.DAO.AnswerBoardDAO;
import Model.DTO.AnswerBoardDTO;

@Service
public class BoardListService {
	
	@Autowired
	AnswerBoardDAO answerBoardDAO;
	
	
	public void getBoardList(Model model, Integer page) { //Integer page를 준 이유는 null떄문에 int가 아닌
		
		
		
		
		int nowpage = 1; //현재 페이지를 알기 위한 변수
		
		if(page != null) {
			nowpage = page; //현재 내가 받은 값을 nowpage에 넣겠다. 널이아니면!!
		}
		
		
		int limit = 10; //한 페이지에 출력될 리스트 수
		int limitPage = 10; //페이지 번호의 출력 개수 (1, 2, 3, 4, 5, 6, 7, 8, 9, 10) (11, 12, 13 ... 20) 이런식으로
		
		//현재 2page라면 리스트는 11번째 (게시글 20번째)..... 내가 만약 3page를 선택했으면 리스트는 21번째 ..게시글 30번째 까지  가져오기 위해서는 limit 
		List<AnswerBoardDTO> boards = answerBoardDAO.boardListAll(nowpage, limit); // 게시글을 limit만큼만 가져온다.
		
		
		
		int totalCount = answerBoardDAO.getBoardCount(); // 전체 게시글의 수
		
		//maxPage가 중요하다 옆에 (int)((double)totalCount/limit + 0.95)는 바뀔수가 있지 그니까 maxPage가 중요한거야 저 옆의 값은 사람마다 달라
		int maxPage = (int)((double)totalCount/limit + 0.95); //현재 페이지 수 recode(행, 리스트, row)가 13이고 limit가 10일때 최대 페이지 수는? 2페이지
		
		int startPage = (int)(((double)nowpage/limitPage + 0.9) -1) * 10 + 1; //1~10 사이의 페이지 번호를 선택했을 첫 페이지의 번호는 1?
		
		
		int endPage = startPage + limitPage -1; //1~10 사이의 페이지 번호를 선택했을 마지막 페이지 번호는 10?
		// 1 2 3 4 5 6 7 8 9 10 에서 start는 1 end는 10 11 12 13 14 15 16 17 18 19 20 에서 start는 11 end는 20 이지요
		
		if(endPage > maxPage) endPage = maxPage; //endPage가 maxPage여야지 ..
		
		
		
		model.addAttribute("boards", boards);
		
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("maxPage", maxPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("page", nowpage);
		
	}

}
