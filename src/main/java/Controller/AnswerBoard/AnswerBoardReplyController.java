package Controller.AnswerBoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Service.AnswerBoard.AnswerBoardReplyService;

@Controller
public class AnswerBoardReplyController {
	
	@Autowired
	AnswerBoardReplyService answerBoardReplyService;
	
	//왜 여기에 적었냐면 디테일서비스를 그대로 써야해서 여기다 썻다는데?
		// 부모글에 대한 상세정보를 가져올 수 있데 그게 위에 내용이라는데 그럼 굳이 컨트롤러를 또 하나 만들 필요가 없데.. // ?? 뭔 개소리야  => 왜 또 갑자기 일로왔어 디테일에 있다가
		
		@RequestMapping("/board/answerBoardReply")
		public String ReplyView(@RequestParam( value = "num" ) Long boardNum, Model model) { //커맨드 객체는 어디서든 만들어도 상관이 없어 값저장만 하면되느까
			
			answerBoardReplyService.boardView(boardNum, model);
			return "AnswerBoard/boardReply";
		}
	
}
