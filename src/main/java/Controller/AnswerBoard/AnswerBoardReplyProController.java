package Controller.AnswerBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;

import Command.AnswerBoard.BoardReplyCommand;
import Service.AnswerBoard.AnswerBoardReplyProService;
import Validator.AnswerBoardValidator;

@Controller
public class AnswerBoardReplyProController {

	
	@Autowired
	AnswerBoardReplyProService answerBoardReplyProService;
	
	@RequestMapping("/board/answerBoardReplyPro")
	public String ReplyPro(BoardReplyCommand boardReplyCommand, Errors errors, HttpSession session, HttpServletRequest request) {
		new AnswerBoardValidator().validate(boardReplyCommand, errors);
		if(errors.hasErrors()) {
			System.out.println("submit");
			return "AnswerBoard/boardReply";
		}
		answerBoardReplyProService.ReplyPro(boardReplyCommand, session, request);
		return "redirect:/board/answerBoardList";
	}
	
	
}
