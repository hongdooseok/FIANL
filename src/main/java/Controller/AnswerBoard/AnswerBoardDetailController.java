package Controller.AnswerBoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Service.AnswerBoard.AnswerBoardDetailService;
import Service.AnswerBoard.AnswerBoardReplyService;

@Controller
public class AnswerBoardDetailController {

	@Autowired
	AnswerBoardDetailService answerBoardDetailService;
	
	
	@RequestMapping("/board/answerBoardDetail")
	public String Detail(@RequestParam(value = "num") Long boardNum, Model model) {  
		//어떤 데이터 형으로 받을거야 << 번호니까 롱이라는데 / 리콰이어드 => 안날라 올 수 도 있으니까 주는거래 이거는 무조건 날라오니까 클릭하면 
		//(원래 쿼리스트링은 스트링이지만 여기서는 알아서 형변환을 해준데) => jsp는 스트링.
		
		answerBoardDetailService.boardView(model, boardNum);

		return "AnswerBoard/board_view";
	}
	
	
	
}
