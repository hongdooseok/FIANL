package Controller.AnswerBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import Command.AnswerBoard.AnswerBoardCommand;
import Service.AnswerBoard.BoardListService;
import Service.AnswerBoard.BoardWriteService;

@Controller
public class AnswerBoardController {
	
	@Autowired
	private BoardWriteService boardWriteService;
	
	@Autowired
	private BoardListService boardListService;

	@RequestMapping("/board/answerBoard")
	public String form(AnswerBoardCommand answerBoardCommand) {
		
		return "AnswerBoard/Board_write";
	}
	
	
	
	@RequestMapping(value ="/board/answerBoardWritePro", method=RequestMethod.POST) //그냥 value주고 해도 나쁘지 않음. 왜? 파일이 post때 하니까 굳이 안써도 되긴함.
	public String Write(AnswerBoardCommand answerBoardCommand, HttpServletRequest request, HttpSession session) { 
		//서비스에서 경로를 파일 경로를 알기위해서 request가 필요해서 여기에 작성합니다. 
		//userId 를 알기 위해서 session을 적어줌.
		
		boardWriteService.boardWrite(answerBoardCommand, request, session);
		
		return "redirect:/board/answerBoardList";
	}
	
	@RequestMapping("/board/answerBoardList")
	public String boardList(@RequestParam(value ="page", required = false) Integer page, Model model ) {
		
		boardListService.getBoardList(model, page); //이것을 board_list여기로 보내려면 뭐가 필요해?? Model이 필요하죠
		
		return "AnswerBoard/board_list";
	}
	
}
