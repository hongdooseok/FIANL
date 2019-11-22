package Service.AnswerBoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import Model.DAO.AnswerBoardDAO;
import Model.DTO.AnswerBoardDTO;

@Service
public class AnswerBoardDetailService {
	
	@Autowired
	private AnswerBoardDAO answerBoardDAO;  
	
	public void boardView(Model model, Long boardNum) {
		//여기는 Detail가 쓰는곳
		answerBoardDAO.boardReadcountUpdate(boardNum);
		System.out.println(boardNum);
		
		AnswerBoardDTO answerBoardDTO = answerBoardDAO.boardDetail(boardNum);
		
		answerBoardDTO.setBoardContent(answerBoardDTO.getBoardContent().replace("\n", "<br />")); //br은 먹는데 \n은 안먹는데 뭔소리야
		
		if(answerBoardDTO.getOriginalFilename() != null) {
		//얘가 파일이 여러개일 수 도 있으니까 스플릿해
			String [] original = answerBoardDTO.getOriginalFilename().split("-"); 
			String [] store = answerBoardDTO.getStoreFilename().split("-");
			String [] fileSize = answerBoardDTO.getFileSize().split("-");
		
			model.addAttribute("fileSize", fileSize);
			model.addAttribute("original", original);
			model.addAttribute("store", store);
		}
		model.addAttribute("board", answerBoardDTO);

		
		
	}

}
