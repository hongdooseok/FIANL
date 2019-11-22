package Service.AnswerBoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import Command.AnswerBoard.BoardReplyCommand;
import Model.DAO.AnswerBoardDAO;
import Model.DTO.AnswerBoardDTO;

@Service
public class AnswerBoardReplyService {

	@Autowired
	private AnswerBoardDAO answerBoardDAO;
	
	public void boardView(Long boardNum, Model model ) {
		BoardReplyCommand boardReplyCommand = new BoardReplyCommand();
		answerBoardDAO.boardReadcountUpdate(boardNum);
		System.out.println(boardNum);
		
		
		AnswerBoardDTO answerBoardDTO = answerBoardDAO.boardDetail(boardNum);
		
		//여기는 Reply가 쓰는곳
		
		
		//커맨드 객체 불러와서 저장한다는데 저거는 AnswerBoardCommand니까 불러와서
		boardReplyCommand.getAnswerBoardCommand().setBoardName(answerBoardDTO.getBoardName());
		boardReplyCommand.getAnswerBoardCommand().setBoardSubject("Re : " + answerBoardDTO.getBoardSubject());
		boardReplyCommand.getAnswerBoardCommand().setBoardContent(answerBoardDTO.getBoardContent() + "\n;================\\n;================\\n;================\\n;================");
		boardReplyCommand.setBoardNum(answerBoardDTO.getBoardNum().toString());
		boardReplyCommand.setBoardReLev(answerBoardDTO.getBoardReLev().toString());
		boardReplyCommand.setBoardReRef(answerBoardDTO.getBoardReRef().toString());
		boardReplyCommand.setBoardReSeq(answerBoardDTO.getBoardReSeq().toString());
		
		model.addAttribute("boardReplyCommand", boardReplyCommand);
	}
}
