package Service.AnswerBoard;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Command.AnswerBoard.BoardReplyCommand;
import Model.DAO.AnswerBoardDAO;
import Model.DTO.AnswerBoardDTO;
import Model.DTO.AuthInfo;

@Service
public class AnswerBoardReplyProService {

	@Autowired
	private AnswerBoardDAO dao;
	
	//파일을 하기 위해서 필요한 내용들
	String original = null;
	String originalFileExtension = null;
	String store = null;
	String fileSize = null;
	Boolean stop = true;
	
	public void ReplyPro(BoardReplyCommand boardReplyCommand, HttpSession session, HttpServletRequest request) { //파일 경로를 알기위해서 rqeuset
		
		AnswerBoardDTO board = new AnswerBoardDTO();
		board.setBoardNum(Long.valueOf(boardReplyCommand.getBoardNum()));
		board.setBoardReRef(Long.valueOf(boardReplyCommand.getBoardReRef()));
		board.setBoardReLev(Long.valueOf(boardReplyCommand.getBoardReLev()));
		board.setBoardReSeq(Long.valueOf(boardReplyCommand.getBoardReSeq()));
		board.setBoardName(boardReplyCommand.getAnswerBoardCommand().getBoardName());
		board.setBoardSubject(boardReplyCommand.getAnswerBoardCommand().getBoardSubject());
		board.setBoardContent(boardReplyCommand.getAnswerBoardCommand().getBoardContent());
		board.setBoardPass(boardReplyCommand.getAnswerBoardCommand().getBoardPass());
		board.setUserId(((AuthInfo)session.getAttribute("authInfo")).getId());
		
		//파일명 가져오는거
		String originalTotal = "";
		String fileSizeTotal = "";
		String storeTotal = "";
		
		
		for(MultipartFile mf : boardReplyCommand.getReports()) {
			original = mf.getOriginalFilename();
			try { // 파일이 없다면 X!~
				originalFileExtension = original.substring(original.lastIndexOf("."));
			
			} catch(Exception e) {
				break;
			}
			store = UUID.randomUUID().toString().replaceAll("-", "") + originalFileExtension;
			
			fileSize = Long.toString(mf.getSize());
			
			originalTotal += original + "-";
			fileSizeTotal += fileSize + "-";
			storeTotal += store + "-";
			
			String path = request.getServletContext().getRealPath("/");
			path += "WEB-INF/view/AnswerBoard/upload/";
			
			File file = new File(path + store);
			try { //파일이 있을경우에는 저장이잖아
				mf.transferTo(file);
			} 
			 catch (Exception e) {
				stop = false;
				e.printStackTrace();
				break;	
			}
		}
		
		if(stop) {
			board.setOriginalFilename(originalTotal);
			board.setStoreFilename(storeTotal);
			board.setFileSize(fileSizeTotal);
			dao.boardReplyInsert(board);
		}
		
		
	}
}
